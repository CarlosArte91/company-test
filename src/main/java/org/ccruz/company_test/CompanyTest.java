package org.ccruz.company_test;

import org.ccruz.company_test.model.Employee;
import org.ccruz.company_test.repository.EmployeeRepository;
import org.ccruz.company_test.repository.Repository;

import java.sql.SQLException;

public class CompanyTest {
    public static void main(String[] args) {
        Repository<Employee> repository = new EmployeeRepository();

        try {
            repository.findAll().forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
