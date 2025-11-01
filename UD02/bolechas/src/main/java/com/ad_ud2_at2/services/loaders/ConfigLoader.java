package com.ad_ud2_at2.services.loaders;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import com.ad_ud2_at2.services.logger.Logger;

public class ConfigLoader {
    
    private static final Properties props = new Properties();
    private static volatile ConfigLoader instance;
    
    private ConfigLoader(){
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                Logger.critical("No se ha encontrado 'application.properties'");
                throw new RuntimeException();
            }
            props.load(input);
        } catch (IOException ex) {
            Logger.critical("Error cargando el fichero de configuración");
            throw new RuntimeException(ex.getMessage());
        }
    }

    public static ConfigLoader getInstance() {
        ConfigLoader localInstance = instance;
        if (localInstance == null) {
            synchronized (ConfigLoader.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ConfigLoader();
                }
            }
        }
        return localInstance;
    }

    public static String get(String key){
        return props.getProperty(key);
    }
}
