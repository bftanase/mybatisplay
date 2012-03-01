package org.apache.ibatis.transaction.jdbc;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;

public class JdbcTransactionFactory implements TransactionFactory {

  public void setProperties(Properties props) {
  }

  public Transaction newTransaction(Connection conn, boolean autoCommit) {
    return new JdbcTransaction(conn, autoCommit);
  }
}
