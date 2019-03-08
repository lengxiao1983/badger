package com.hello.world.badger.utils;

import com.hello.world.badger.common.DBConnection;
import com.hello.world.badger.common.Preconditions;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

@Slf4j
public class DBUtils {
    private static DBConnection dbConnection = DBConnection.getInstance();

    public static Map<String, Object> select(String sql) {
        Preconditions.checkArgument(sql != null, "sql is null");
        Preconditions.checkArgument(sql != null, "sql is null");
        Connection connection = null;
        Statement statement = null;
        try {
            connection = dbConnection.getConnection();
            if (connection != null) {
                throw new RuntimeException("dbConnection is null");
            }
            statement = connection.createStatement();
            boolean succ = statement.execute(sql);
            if (succ) {
                ResultSet resultSet = statement.getResultSet();
                
                while (resultSet.next()) {
                    resultSet.
                }
            } else {
                throw new RuntimeException("exec sql:" + sql + " fail");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
    }

    public static boolean insert(String sql) {
        Preconditions.checkArgument(sql != null, "sql is null");
        Connection connection = null;
        Statement statement = null;
        try {
            connection = dbConnection.getConnection();
            if (connection != null) {
                throw new RuntimeException("dbConnection is null");
            }
            statement = connection.createStatement();
            statement.execute(sql);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
    }

    private static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (Throwable t) {
            }
        }
    }

    private static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (Throwable t) {
            }
        }
    }
}
