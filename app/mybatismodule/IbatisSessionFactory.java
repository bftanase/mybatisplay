package mybatismodule;

import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import play.Play;
import ro.btanase.play.plugins.FileChangeCallback;
import ro.btanase.play.plugins.MyBatisPlugin;

public class IbatisSessionFactory implements FileChangeCallback{
  private SqlSessionFactory sessionFactory;

  private static IbatisSessionFactory instance;
  
  private IbatisSessionFactory(){
    MyBatisPlugin.getInstance().setCallback(this);
    instance = this;
  }
  
  
  

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

      sessionFactory = new SqlSessionFactoryBuilder().build(reader, prop);

    }
    
    return sessionFactory;
  }

  
  @Override
  public void onFileChange() {
    System.out.println("resetting session factory");
    sessionFactory = null;
    
  }
}
