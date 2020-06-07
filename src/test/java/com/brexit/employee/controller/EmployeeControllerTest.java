package com.brexit.employee.controller;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.UUID;

import com.brexit.employee.model.Employee;
import com.brexit.employee.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = EmployeeController.class)
//@SpringBootTest(classes = EmployeeApplication.class)
//@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void saveEmployee() throws Exception {

        //Input object to deserialize
        Employee employee = new Employee();
        employee.setName("Sandhya");
        employee.setSurname("Fulzele");

        //Mocking service layer
        Employee employeeReturned = new Employee();
        employeeReturned.setName("Sandhya");
        employeeReturned.setSurname("Fulzele");
        employeeReturned.setCreatedAt(new Date());
        employeeReturned.setId(UUID.randomUUID());
        doReturn(employeeReturned).when(employeeService).saveEmployee(employee);


        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        //.andExpect(jsonPath("$.name", is(employeeReturned.getName())));
        // .andExpect(content().string(equalTo("Greetings from Spring Boot!")));
    }

}
