package com.brexit.employee.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import com.brexit.employee.model.Address;
import com.brexit.employee.model.Employee;
import com.brexit.employee.repository.AddressRepository;
import com.brexit.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private AddressRepository addressRepository;

    @DisplayName("should be able to add address")
    @Test
    public void shouldAddAddress() {

        //Given
        EmployeeService employeeService = new EmployeeService(employeeRepository, addressRepository);
        UUID employeeId = UUID.randomUUID();
        Address address = getStubbedAddress();
        Employee employee = getStubbedEmployee(employeeId);
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        when(addressRepository.save(address)).thenReturn(address);

        //When
        final Address address1 = employeeService.addAddress(employeeId, address);

        //Then
        assertEquals(address.getAddressLine1(), address1.getAddressLine1());
        verify(employeeRepository,times(1)).findById(employeeId);
        verify(addressRepository,times(1)).save(address);
    }

    @DisplayName("should throw employee not found exception while adding address for an employee id which do not exist")
    @Test
    public void shouldThrowEmployeeNotFoundExceptionWhenAddingAddress() {

        //Given
        EmployeeService employeeService = new EmployeeService(employeeRepository, addressRepository);
        UUID employeeId = UUID.randomUUID();
        Address address = getStubbedAddress();
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

        //When and Then
        Assertions.assertThrows(EmployeeNotFoundException.class,() -> employeeService.addAddress(employeeId, address));
    }

    private Employee getStubbedEmployee(final UUID employeeId) {
        Employee employee = new Employee();
        employee.setId(employeeId);
        employee.setCreatedAt(new Date());
        employee.setSurname("Bar");
        employee.setName("Foo");
        return employee;
    }

    private Address getStubbedAddress() {
        Address address = new Address();
        address.setPostCode("XXX-43YY");
        address.setCountry("UK");
        address.setCity("London");
        address.setAddressLine1("Benson Road");
        return address;
    }
}
