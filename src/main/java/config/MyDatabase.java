package config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SuppressWarnings("squid:S2115")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MyDatabase {
    public static Connection connect() throws SQLException {
        try {
            var url = System.getenv("database_url");
            var user = System.getenv("database_username");
            var pass = System.getenv("database_password");

            var connection = DriverManager.getConnection(url, user, pass);
            Logger.systemInfo("Success login to database", "MySQL Database");
            return connection;

        } catch (SQLException exception) {
            Logger.systemInfo("Failed login to database", "MySQL Database");
            throw exception;
        }
    }
}
