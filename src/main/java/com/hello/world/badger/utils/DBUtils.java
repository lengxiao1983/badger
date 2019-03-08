package com.hello.world.badger.utils;

import com.hello.world.badger.common.DBConnection;
import com.hello.world.badger.common.Preconditions;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class DBUtils {
    private static DBConnection dbConnection = DBConnection.getInstance();

    public static List<Map<String, Object>> select(String sql) {
        Preconditions.checkNotNull(sql, "sql is null");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dbConnection.getConnection();
            if (connection != null) {
                throw new RuntimeException("dbConnection is null");
            }
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            resultSet = preparedStatement.getResultSet();
            String[] cols = extractColumLabels(resultSet);
            List<Map<String, Object>> rows = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i=0; i<cols.length; i++) {
                    row.put(cols[i], resultSet.getObject(cols[i]));
                }
                rows.add(row);
            }
            return rows;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    private static String[] extractColumLabels(ResultSet resultSet) throws Exception {
        Preconditions.checkNotNull(resultSet, "resultSet is null");
        int count = resultSet.getMetaData().getColumnCount();
        String[] cols = new String[count];
        for (int i=0; i<count; i++) {
            cols[i] = resultSet.getMetaData().getColumnLabel(i);
        }
        return cols;
    }

    public static boolean insert(String sql) {
        return update(sql);
    }

    public static boolean update(String sql) {
        Preconditions.checkNotNull(sql, "sql is null");
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

    private static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (Throwable t) {

            }
        }
    }
}
