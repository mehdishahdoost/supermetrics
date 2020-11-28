package com.supermetrics.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Configs - Loads configs  from config.properties file.
 *
 * @author Mehdi Shahdoost
 */
public final class Configs {

    private final Logger LOG = LoggerFactory.getLogger(Configs.class);
    private static Configs instance;
    private Properties properties = new Properties();

    private Configs() {
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream("configs.properties")) {
            properties.load(inputStream);
        } catch (IOException exception) {
            LOG.error(exception.getMessage());
        }
    }

    public static synchronized Configs getInstance() {
        if (instance == null)
            instance = new Configs();
        return instance;
    }

    public String getClientId() {
        return properties.getProperty("supermetrics.client.id");
    }

    public String getEmail() {
        return properties.getProperty("supermetrics.client.email");
    }

    public String getName() {
        return properties.getProperty("supermetrics.client.name");
    }

    public String getUrl() {
        return properties.getProperty("supermetrics.url");
    }
}
