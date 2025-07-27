package com.jeyanth.config;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
public class ConfigLoader {
    public static Properties loadConfig() throws IOException {
        Properties props = new Properties();
        try (InputStream input = new FileInputStream("config.properties")) {
            props.load(input);
        }
        return props;
    }
}
