package DbPersistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnectionManagement {

    private static DbConnectionManagement instance;
    private final Connection connection;

    private DbConnectionManagement() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/DbPersistence/dbconfig.properties"));

            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");

            this.connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        if (this.connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
    }

    public Connection getConnection() {
        return  connection;
    }

    public static DbConnectionManagement getInstance() {
        try {
            if (instance == null || instance.connection.isClosed()) {
                synchronized (DbConnectionManagement.class) {
                    if (instance == null || instance.connection.isClosed()) {
                        instance = new DbConnectionManagement();
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return instance;
    }
}
