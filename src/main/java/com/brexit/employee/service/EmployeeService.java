package com.brexit.employee.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.brexit.employee.model.Address;
import com.brexit.employee.model.Employee;
import com.brexit.employee.repository.AddressRepository;
import com.brexit.employee.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    private AddressRepository addressRepository;

    public EmployeeService(final EmployeeRepository employeeRepository, final AddressRepository addressRepository) {
        this.employeeRepository = employeeRepository;
        this.addressRepository = addressRepository;
    }

    public Optional<Employee> findEmployeeById(UUID id) {
        return employeeRepository.findById(id);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Address> findEmployeeAddresses(UUID employeeId) {
        return addressRepository.findByEmployeeId(employeeId);
    }

    public Address addAddress(UUID employeeId, Address address) {
        final Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            address.setEmployee(employeeOptional.get());
            return addressRepository.save(address);
        }
        throw new EmployeeNotFoundException("Employee not found :" + employeeId);
    }

    public Page<Employee> findAllEmployee(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public void deleteEmployee(UUID id) {
        final Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            employeeRepository.delete(employeeOptional.get());
        }
    }


}
