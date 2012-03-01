package org.apache.ibatis.executor.statement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.session.ResultHandler;

public interface StatementHandler {

  Statement prepare(Connection connection)
      throws SQLException;

  void parameterize(Statement statement)
      throws SQLException;

  void batch(Statement statement)
      throws SQLException;

  int update(Statement statement)
      throws SQLException;

  List query(Statement statement, ResultHandler resultHandler)
      throws SQLException;

  BoundSql getBoundSql();

  ParameterHandler getParameterHandler();

}
