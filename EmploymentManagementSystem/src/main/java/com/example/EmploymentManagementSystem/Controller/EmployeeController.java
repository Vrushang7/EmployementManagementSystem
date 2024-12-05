package com.example.EmploymentManagementSystem.Controller;

import com.example.EmploymentManagementSystem.Model.Employee;
import com.example.EmploymentManagementSystem.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    // CREATE: Add new employee
    @PostMapping
    public ResponseEntity<String> addEmployee(@RequestBody List<Employee> employee) {
        int result = employeeRepository.addEmployee(employee);
        if (result > 0) {
            return ResponseEntity.ok("Employee added successfully.");
        } else {
            return ResponseEntity.status(500).body("Error adding employee.");
        }
    }

    // READ: Get all employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    // READ: Get employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
        Employee employee = employeeRepository.getEmployeeById(id);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // UPDATE: Update employee details
    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable int id, @RequestBody Employee employeeDetails) {
        int result = employeeRepository.updateEmployee(id, employeeDetails);
        if (result > 0) {
            return ResponseEntity.ok("Employee updated successfully.");
        } else {
            return ResponseEntity.status(500).body("Error updating employee.");
        }
    }

    // DELETE: Delete employee by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
        int result = employeeRepository.deleteEmployee(id);
        if (result > 0) {
            return ResponseEntity.ok("Employee deleted successfully.");
        } else {
            return ResponseEntity.status(500).body("Error deleting employee.");
        }
    }
}
