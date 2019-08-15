package com.sf.jkt.k.comp.javaagent.mysql;


import com.mysql.jdbc.MysqlIO;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetInternalMethods;
import com.mysql.jdbc.Statement;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import net.bytebuddy.implementation.bind.annotation.This;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.concurrent.Callable;

/**
 * Created by dragon on 16/3/29.
 */
public class MysqlInterceptor {

  public static ResultSetInternalMethods sqlQueryDirect(
      @This
      Object zuper,
      @SuperCall
      Callable<ResultSetInternalMethods> client,
      @AllArguments
      Object[] args) throws Exception {


    long start = System.currentTimeMillis();
    Statement statement = (Statement) args[0];
    if (statement instanceof PreparedStatement) {
      try {
        String sql = ((PreparedStatement) statement).asSql();
        InetSocketAddress inetSocketAddress = (InetSocketAddress)((MysqlIO)zuper).mysqlConnection.getRemoteSocketAddress();
        int ipv4 = ByteBuffer.wrap(inetSocketAddress.getAddress().getAddress()).getInt();
        int port = inetSocketAddress.getPort();
        System.out.println("ipv4 is " + ipv4 + ", port is " + port);
        ResultSetInternalMethods response = client.call();
        System.out.println("sql is " + sql + ", person is ");
        return response;
      } catch (Exception e) {
        throw e;
      }
    } else {
      try {
        ResultSetInternalMethods response = client.call();
        return response;
      } catch (Exception e) {
        throw e;
      }
    }
  }
}
