package com.firstbasehq.employee.management.controller;

import com.firstbasehq.employee.management.AbstractTest;
import com.firstbasehq.employee.management.dto.request.CreateEmployeeRequest;
import com.firstbasehq.employee.management.dto.request.UpdateEmployeeRequest;
import com.firstbasehq.employee.management.model.Employee;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Art Yesipovich 7/15/21
 */

public class EmployeeControllerTest extends AbstractTest {
    private static final String PATH = "/employee";

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getEmployess_success() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(PATH)
                                 .accept(MediaType.APPLICATION_JSON_VALUE))
                                 .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        Employee[] employees = super.mapFromJson(content, Employee[].class);
        assertEquals(200, status);
        assertTrue(employees.length > 0);
    }

    @Test
    public void createEmployee_success() throws Exception {
        CreateEmployeeRequest createEmployeeRequest = CreateEmployeeRequest
                .builder()
                .jobTitle("Software Engineer")
                .build();
        String inputJson = super.mapToJson(createEmployeeRequest);
        mvc.perform(MockMvcRequestBuilders.post(PATH)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.jobTitle").value(createEmployeeRequest.getJobTitle()))
                .andReturn();
    }

    @Test
    public void createEmployee_missingJobTitle() throws Exception {
        CreateEmployeeRequest createEmployeeRequest = CreateEmployeeRequest
                .builder()
                .build();
        String inputJson = super.mapToJson(createEmployeeRequest);
        mvc.perform(MockMvcRequestBuilders.post(PATH)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void updateEmployee_success() throws Exception {
        String path = "/employee/1";
        UpdateEmployeeRequest updateEmployeeRequest = UpdateEmployeeRequest
                .builder()
                .firstName("Test Name")
                .build();
        String inputJson = super.mapToJson(updateEmployeeRequest);
        mvc.perform(MockMvcRequestBuilders.put(path)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(updateEmployeeRequest.getFirstName()))
                .andReturn();
    }
}
