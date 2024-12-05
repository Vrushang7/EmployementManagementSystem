package com.example.EmploymentManagementSystem.Repository;

import com.example.EmploymentManagementSystem.Model.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmployeeRepository {
    private final JdbcTemplate jdbcTemplate;

    public EmployeeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Add employee to the database
    public int addEmployee(List<Employee> employeeList) {
        try {
            for (Employee employee : employeeList) {
                String sql = "INSERT INTO employee (id, name, department, salary) VALUES (?, ?, ?, ?)";
                 jdbcTemplate.update(sql, employee.getId(), employee.getName(), employee.getDepartment(), employee.getSalary());

            }
        } catch (Exception e) {
            return 0;
        }
        return 0;
    }

    // Update employee details in the database
    public int updateEmployee(int id, Employee employee) {
        String sql = "UPDATE employee SET name = ?, department = ?, salary = ? WHERE id = ?";
        return jdbcTemplate.update(sql, employee.getName(), employee.getDepartment(), employee.getSalary(), id);
    }

    // Delete employee from the database
    public int deleteEmployee(int id) {
        String sql = "DELETE FROM employee WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    // Get employee by ID
    public Employee getEmployeeById(int id) {
        String sql = "SELECT * FROM employee WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new EmployeeRowMapper());
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        String sql = "SELECT * FROM employee ";
        return jdbcTemplate.query(sql, new EmployeeRowMapper());
    }

    private static class EmployeeRowMapper implements RowMapper<Employee> {
        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException, SQLException {
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setName(rs.getString("name"));
            employee.setDepartment(rs.getString("department"));
            employee.setSalary(rs.getDouble("salary"));
            return employee;
        }
    }
}
