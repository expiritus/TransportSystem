package com.belhard.misha.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

public class PropertyUtils {


    public PropertyUtils() {
        throw new InstantiationError("Class contains static method only. You should not instantiate it!");
    }

    public static Properties getValidProperties(){
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("/settings/error-valid.properties");
        Properties properties = new Properties();
        try {
            properties.load(new InputStreamReader(stream, Charset.forName("utf8")));
        } catch (IOException e){
            throw new RuntimeException("File error-valid.properties can not be loaded", e);
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties;
    }
}
