package ru.javawebinar.basejava;

import ru.javawebinar.basejava.storage.SqlStorage;
import ru.javawebinar.basejava.storage.Storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final File PROPS = new File("config\\resumes.properties");
    private static final Config INSTANCE = new Config();
    private final File storageDir;
    private final Storage storage;
    ;

    public File getStorageDir() {
        return storageDir;
    }

    private Config() {
        try (InputStream is = new FileInputStream(PROPS)) {
            Properties prop = new Properties();
            prop.load(is);
            storageDir = new File(prop.getProperty("storage.dir"));
            storage = new SqlStorage(prop.getProperty("db.url"), prop.getProperty("db.user"), prop.getProperty("db.password"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPS.getAbsolutePath());
        }
    }

    public static Config getInstance() {
        return INSTANCE;
    }

    public Storage getStorage() {
        return storage;
    }
}
