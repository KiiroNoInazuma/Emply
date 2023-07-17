package connect;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectMyBase {
    private static Properties loadConfig() throws Exception {
        Properties config = new Properties();
        try (FileReader fileReader = new FileReader("src/main/resources/application.properties")) {
            config.load(fileReader);
        }
        return config;
    }

    public static Connection getConnect(String link, String username, String password) {
        Properties properties;
        Connection connection;
        try {
            properties = loadConfig();
            String url = properties.getProperty(link);
            String user = properties.getProperty(username);
            String pass = properties.getProperty(password);
            connection = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

}
