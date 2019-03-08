package com.hello.world.badger.config;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

import static com.hello.world.badger.common.Constants.CONFIG_PATH;

@Slf4j
public class AppConfig {

    private final static AppConfig instance = new AppConfig();
    private final static Properties properties = new Properties();
    private String configFile;


    private AppConfig() {
        load();
    }

    private void load() {
        BufferedReader bufferedReader = null;
        try {
            configFile = System.getProperty(CONFIG_PATH);
            bufferedReader = new BufferedReader(new FileReader(configFile));
            properties.load(bufferedReader);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception t) {
                    log.error(t.getMessage(), t);
                }
            }
        }
    }

    public static String getConfigString(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public static String getConfigString(String key) {
        return properties.getProperty(key);
    }
}
