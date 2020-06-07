package com.brexit.employee.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import com.brexit.employee.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class AddressRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Test
    public void shouldSaveEmployee() {
        Employee employee = new Employee();
        employee.setCreatedAt(new Date());
        employee.setName("Sandhya");
        employee.setSurname("Fulzele");

        final Employee employee1 = employeeRepository.save(employee);
        assertNotNull(employee1.getId());
    }


    //ToDo : exception handling with mock
}
