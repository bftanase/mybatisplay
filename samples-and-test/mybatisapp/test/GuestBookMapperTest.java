import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.*;

import java.io.IOException;
import java.io.Reader;
import java.util.*;
import play.test.*;
import models.*;
import models.domain.GuestBook;
import models.mybatis.mappers.GuestBookMapper;
import mybatisplay.IbatisSessionFactory;

public class GuestBookMapperTest extends UnitTest {

  @Before
  public void setUp() throws IOException {
    SqlSessionFactory sessionFactory = IbatisSessionFactory.get();
    SqlSession session = sessionFactory.openSession();

    String import_script = "models/scripts/import-h2.sql";
    Reader script_reader = Resources.getResourceAsReader(import_script);

    ScriptRunner scriptRunner = new ScriptRunner(session.getConnection());
    scriptRunner.runScript(script_reader);
    session.commit();

  }

  @Test
  public void testSelectAll(){
    SqlSession session = IbatisSessionFactory.get().openSession();
    
    GuestBookMapper mapper = session.getMapper(GuestBookMapper.class);
    
    List<GuestBook> guestList = mapper.selectAll();
    
    assertEquals(2, guestList.size());
    
    assertEquals("John Doe", guestList.get(0).getName());
    
    session.close();
  }

}
