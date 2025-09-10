package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Config INSTANCE = new Config();

    private final File prop = new File("C:\\Java\\basejava\\config\\resumes.properties");
    private final File storageDir;

    public File getStorageDir() {
        return storageDir;
    }

    private Config() {
        try (InputStream is = new FileInputStream(prop)) {
            Properties prop = new Properties();
            prop.load(is);
            storageDir = new File(prop.getProperty("storage.dir"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + prop.getAbsolutePath());
        }
    }

    public static Config getInstance() {
        return INSTANCE;
    }
}
