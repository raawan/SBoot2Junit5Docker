package com.brexit.employee.controller;

import java.util.List;
import java.util.UUID;

import com.brexit.employee.model.Address;
import com.brexit.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {

    @Autowired
    private EmployeeService service;

    @PostMapping("/employees/{employeeId}/address")
    public Address addAddress(@PathVariable UUID employeeId, @Validated @RequestBody Address address) {
        return service.addAddress(employeeId, address);
    }

    @GetMapping("/employees/{employeeId}/address")
    public List<Address> getEmployeeAddresses(@PathVariable UUID employeeId) {
        return service.findEmployeeAddresses(employeeId);
    }
}
