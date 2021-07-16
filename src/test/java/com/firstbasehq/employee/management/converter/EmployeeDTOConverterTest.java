package com.firstbasehq.employee.management.converter;

import com.firstbasehq.employee.management.TestUtils;
import com.firstbasehq.employee.management.dto.EmployeeDTO;
import com.firstbasehq.employee.management.dto.converter.EmployeeDTOConverter;
import com.firstbasehq.employee.management.model.Employee;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

/**
 * @author Art Yesipovich 7/16/21
 */

public class EmployeeDTOConverterTest {

    @InjectMocks
    EmployeeDTOConverter employeeDTOConverter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void convert_success() {
        Employee employee = TestUtils.buildEmployee();

        EmployeeDTO employeeDTO = employeeDTOConverter.convert(employee);

        assertEquals(employee.getId(), employeeDTO.getId());
        assertEquals(employee.getFirstName(), employeeDTO.getFirstName());
        assertEquals(employee.getLastName(), employeeDTO.getLastName());
        assertEquals(employee.getJobTitle(), employeeDTO.getJobTitle());
        assertEquals(employee.getPhotoURL(), employeeDTO.getPhotoURL());
    }
}
