package org.ccruz.company_test;

import org.ccruz.company_test.util.DataBaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CompanyTest {
    public static void main(String[] args) {
        try (Connection connection = DataBaseConnection.getInstance();
            Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM employees")) {
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getDouble(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
