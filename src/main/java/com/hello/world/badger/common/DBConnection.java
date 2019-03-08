package com.hello.world.badger.common;

import com.hello.world.badger.config.AppConfig;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;

@Slf4j
public class DBConnection {
    org.apache.commons.dbcp.BasicDataSource dataSource;

    private static DBConnection instance = null;

    private static final Object lock = new Object();

    public static DBConnection getInstance() {
        if (instance != null) {
            return instance;
        }
        synchronized (lock) {
            if (instance != null) {
                return instance;
            }
            try {
                instance = new DBConnection();
                return instance;
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                instance = null;
            }
            return instance;
        }
    }

    private DBConnection() throws Exception {
        dataSource = new org.apache.commons.dbcp.BasicDataSource();
        dataSource.setUrl(AppConfig.getConfigString(Constants.MYSQL_URL));
        dataSource.setUsername(AppConfig.getConfigString(Constants.MYSQL_USER));
        dataSource.setPassword(AppConfig.getConfigString(Constants.MYSQL_PASSWD));
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setInitialSize(1);
        dataSource.setMaxActive(10);
        dataSource.setMaxIdle(5);
        dataSource.setMaxWait(1000);
        dataSource.setDefaultAutoCommit(true);
        dataSource.setTestOnBorrow(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setValidationQuery("select 1");
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setTimeBetweenEvictionRunsMillis(180000);
        dataSource.setMinEvictableIdleTimeMillis(3600000);
        dataSource.setNumTestsPerEvictionRun(10);
        dataSource.setRemoveAbandoned(true);
        dataSource.setRemoveAbandonedTimeout(300);
    }

    public Connection getConnection() throws Exception {
        return dataSource.getConnection();
    }

    public void close() {
        if (dataSource != null) {
            try {
                dataSource.close();
            } catch (Throwable t) {
                log.error(t.getMessage(), t);
            } finally {
            }
        }
    }
}
