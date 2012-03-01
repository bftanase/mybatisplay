package mybatisplay;

import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

import mybatisplay.plugins.FileChangeCallback;
import mybatisplay.plugins.MyBatisPlugin;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import play.Play;
/*
 *  This class provides convenient access to SqlSessionFactory. It will read database settings 
 *  from play configuration file (application.conf)
 * 
 *  @author bftanase@gmail.com
 *
 */
public class IbatisSessionFactory implements FileChangeCallback{
  private static Logger log = Logger.getLogger(IbatisSessionFactory.class);
  private SqlSessionFactory sessionFactory;

  private static IbatisSessionFactory instance;
  
  private IbatisSessionFactory(){
    
    MyBatisPlugin.getInstance().setCallback(this);
    instance = this;
  }

  /**
   * <p>Create a unique instance and return a SqlSessionFactory</p>
   * 
   * <p>If the callback isn't called previously it will return the same SqlSessionFactory</p>
   * 
   * @return SqlSessionFactory
   */
  public static SqlSessionFactory get() {
    
    if (instance == null){
      instance = new IbatisSessionFactory();
    }
    
    return instance.getSqlSessionFactory();
    
  }

  private SqlSessionFactory getSqlSessionFactory(){
    if (sessionFactory == null) {
      String resource = Play.configuration.getProperty("mybatis.configuration");
      
      Reader reader;
      
      try {
        reader = Resources.getResourceAsReader(resource);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      
      Properties prop = new Properties();
      
      prop.put("url", Play.configuration.getProperty("db.url"));
      prop.put("user", Play.configuration.getProperty("db.user"));
      prop.put("pass", Play.configuration.getProperty("db.pass"));
      prop.put("driver", Play.configuration.getProperty("db.driver"));

      sessionFactory = new SqlSessionFactoryBuilder().build(reader, prop);

    }
    
    return sessionFactory;
  }

  
  @Override
  public void onFileChange() {
    log.debug("resetting session factory");
    sessionFactory = null;
    
  }
}
