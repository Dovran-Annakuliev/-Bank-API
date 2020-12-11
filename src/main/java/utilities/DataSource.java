package utilities;

import com.zaxxer.hikari.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import org.h2.jdbcx.JdbcConnectionPool;

public class DataSource {
    private static HikariDataSource dataSource;

    static {
        try {
            HikariConfig config = new HikariConfig();
            Properties properties = new Properties();
            properties.load(DataSource.class.getResourceAsStream("/db.properties"));
            config.setJdbcUrl(properties.getProperty("db.url"));
            config.setUsername(properties.getProperty("db.user"));
            config.setPassword(properties.getProperty("db.password"));
            config.setDriverClassName(properties.getProperty("db.driver.name"));
            dataSource = new HikariDataSource(config);

//            JdbcConnectionPool cp = JdbcConnectionPool.create("jdbc:h2:~/test", "sa", "sa");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private DataSource() {}

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
