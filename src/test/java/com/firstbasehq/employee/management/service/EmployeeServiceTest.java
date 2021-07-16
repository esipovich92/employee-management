package com.firstbasehq.employee.management.service;

import com.firstbasehq.employee.management.TestUtils;
import com.firstbasehq.employee.management.dto.EmployeeDTO;
import com.firstbasehq.employee.management.dto.converter.EmployeeDTOConverter;
import com.firstbasehq.employee.management.dto.request.CreateEmployeeRequest;
import com.firstbasehq.employee.management.dto.request.UpdateEmployeeRequest;
import com.firstbasehq.employee.management.integrations.randomuser.RandomUserClient;
import com.firstbasehq.employee.management.integrations.randomuser.RandomUserDTO;
import com.firstbasehq.employee.management.integrations.randomuser.RandomUserException;
import com.firstbasehq.employee.management.integrations.randomuser.RandomUserWrapperDTO;
import com.firstbasehq.employee.management.model.Employee;
import com.firstbasehq.employee.management.repository.EmployeeRepository;
import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * @author Art Yesipovich 7/15/21
 */

public class EmployeeServiceTest {
    @Mock
    EmployeeRepository employeeRepository;
    @Mock
    EmployeeDTOConverter employeeDTOConverter;
    @Mock
    RandomUserClient randomUserClient;
    @Mock
    Page<Employee> page;

    @InjectMocks
    EmployeeServiceImpl employeeService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getEmployees_success() {
        Employee employee = TestUtils.buildEmployee();
        List<Employee> employees = Collections.singletonList(employee);
        when(employeeRepository.findAll(any(Pageable.class)))
                .thenReturn(page);
        when(page.getContent()).thenReturn(employees);
        when(employeeDTOConverter.convert(employee))
                .thenCallRealMethod();

        List<EmployeeDTO> employeesDTO = employeeService.getEmployees(PageRequest.of(0, 1));

        assertEquals(employees.size(), employeesDTO.size());
        EmployeeDTO employeeDTO = employeesDTO.get(0);
        assertEquals(employee.getFirstName(), employeeDTO.getFirstName());
        assertEquals(employee.getLastName(), employeeDTO.getLastName());
        assertEquals(employee.getJobTitle(), employeeDTO.getJobTitle());
        assertEquals(employee.getPhotoURL(), employeeDTO.getPhotoURL());
        verify(employeeRepository, times(1)).findAll(any(Pageable.class));
        verify(employeeDTOConverter, times(1)).convert(employee);
        verifyNoMoreInteractions(employeeRepository, employeeDTOConverter);
    }

    @Test
    public void saveEmployee_success() {
        CreateEmployeeRequest createEmployeeRequest = TestUtils.buildCreateEmployeeRequest();
        when(randomUserClient.getRandomUser()).thenReturn(TestUtils.createRandomUser());
        when(employeeRepository.save(any(Employee.class))).thenReturn(Employee.builder().build());
        when(employeeDTOConverter.convert(any(Employee.class)))
                .thenCallRealMethod();

        employeeService.saveEmployee(createEmployeeRequest);

        verify(randomUserClient, times(1)).getRandomUser();
        verify(employeeRepository, times(1)).save(any(Employee.class));
        verify(employeeDTOConverter, times(1)).convert(any(Employee.class));
        verifyNoMoreInteractions(randomUserClient, employeeRepository, employeeDTOConverter);
    }

    @Test(expected = RandomUserException.class)
    public void saveEmployee_randomUserFailure() {
        CreateEmployeeRequest createEmployeeRequest = TestUtils.buildCreateEmployeeRequest();
        when(randomUserClient.getRandomUser()).thenReturn(RandomUserWrapperDTO.builder().build());

        employeeService.saveEmployee(createEmployeeRequest);
    }

    @Test
    public void updateEmployee_success() {
        Employee employee = TestUtils.buildEmployee();
        UpdateEmployeeRequest updateEmployeeRequest = TestUtils.buildUpdateEmployeeRequest();
        when(employeeRepository.getOne(employee.getId())).thenReturn(employee);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        when(employeeDTOConverter.convert(any(Employee.class)))
                .thenCallRealMethod();

        employeeService.updateEmployee(employee.getId(), updateEmployeeRequest);

        verify(employeeRepository, times(1)).getOne(employee.getId());
        verify(employeeRepository, times(1)).save(any(Employee.class));
        verify(employeeDTOConverter, times(1)).convert(any(Employee.class));
        verifyNoMoreInteractions(randomUserClient, employeeRepository, employeeDTOConverter);
    }

    @Test(expected = EntityNotFoundException.class)
    public void updateEmployee_employeeNotFound() {
        Employee employee = TestUtils.buildEmployee();
        UpdateEmployeeRequest updateEmployeeRequest = TestUtils.buildUpdateEmployeeRequest();
        when(employeeRepository.getOne(employee.getId())).thenThrow(EntityNotFoundException.class);

        employeeService.updateEmployee(employee.getId(), updateEmployeeRequest);
    }
}
