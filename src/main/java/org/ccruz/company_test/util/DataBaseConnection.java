package org.ccruz.company_test.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static final String BASE = "jdbc:mysql://127.0.0.1:3306/";
    private static final String TIMEZONE = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String DB_NAME = "company_test";
    private static final String USER = "root";
    private static final String PASSWORD = "aDMin_8x954j-95";
    private static final String URL = BASE + DB_NAME + TIMEZONE;

    private static Connection connection = null;

    public static Connection getInstance() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }
}
