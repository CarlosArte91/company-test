package org.ccruz.company_test.repository;

import org.ccruz.company_test.model.Employee;
import org.ccruz.company_test.util.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements Repository<Employee> {
    @Override
    public List<Employee> findAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employees";

        try (Connection connection = DataBaseConnection.getInstance();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Employee employee = obtainEmployee(resultSet);
                employees.add(employee);
            }
        }

        return employees;
    }

    @Override
    public Employee getById(Integer id) throws SQLException {
        Employee employee = null;
        String query = "SELECT * FROM employees WHERE id_employee = ?";

        try (Connection connection = DataBaseConnection.getInstance();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    employee = obtainEmployee(resultSet);
                }
            }
        }

        return employee;
    }

    private static Employee obtainEmployee(ResultSet resultSet) throws SQLException {
        return new Employee(
                resultSet.getInt("id_employee"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("email"),
                resultSet.getDouble("salary")
        );
    }

    @Override
    public void save(Employee employee) throws SQLException {
        boolean isNewEmployee = employee.getIdEmployee() == null;
        String query = "";

        if (isNewEmployee) {
            query = "INSERT INTO employees (first_name, last_name, email, salary) VALUES (?, ?, ?, ?)";
        } else {
            query = "UPDATE employees SET first_name = ?, last_name = ?, email = ?, salary = ? WHERE id_employee = ?";
        }

        try (Connection connection = DataBaseConnection.getInstance();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getEmail());
            statement.setDouble(4, employee.getSalary());

            if (!isNewEmployee) {
                statement.setInt(5, employee.getIdEmployee());
            }

            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String query = "DELETE FROM employees WHERE id_employee = ?";

        try (Connection connection = DataBaseConnection.getInstance();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
