package com.brexit.employee.controller;

import java.util.Date;
import java.util.UUID;

import com.brexit.employee.model.Employee;
import com.brexit.employee.service.EmployeeService;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @PostMapping("/employees")
    public Employee addEmployee(@Validated @RequestBody Employee employee) {
        employee.setCreatedAt(new Date());
        final Employee employee1 = service.saveEmployee(employee);
        return employee1;
    }

    @GetMapping("/employees")
    public Page<Employee> getAllEmployees(Pageable pageable) {
        return service.findAllEmployee(pageable);
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable UUID employeeId) {
        return service.findEmployeeById(employeeId).get();
    }

    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@PathVariable UUID employeeId) {
        service.deleteEmployee(employeeId);
    }

    @PutMapping("/employees/{employeeId}")
    public void updateEmployee(@PathVariable UUID employeeId, @RequestBody Employee employee)
            throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Method not implemented");
    }

    @PatchMapping("/employees/{employeeId}")
    public void updateEmployeePartially(@PathVariable UUID employeeId, @RequestBody Employee employee)
            throws ExecutionControl.NotImplementedException {
        throw new UnsupportedOperationException("Method not implemented");
    }
}
