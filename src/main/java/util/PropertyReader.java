package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    public static Properties loadProp(String name) throws IOException {
        Properties prop = new Properties();
        InputStream inputStream = new FileInputStream(Thread.currentThread().getContextClassLoader().getResource("").getPath() + name);
        prop.load(inputStream);
        return prop;
    }
}
