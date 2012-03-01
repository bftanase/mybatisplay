package org.apache.ibatis.executor.keygen;

import java.sql.Statement;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;

public class NoKeyGenerator implements KeyGenerator {

  public void processBefore(Executor executor, MappedStatement ms, Statement stmt, Object parameter) {
  }

  public void processAfter(Executor executor, MappedStatement ms, Statement stmt, Object parameter) {
  }

}
