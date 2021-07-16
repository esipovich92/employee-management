package com.firstbasehq.employee.management.service;

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
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;

/**
 * @author Art Yesipovich 7/15/21
 */

@Component
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeDTOConverter employeeDTOConverter;
    private final RandomUserClient randomUserClient;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               EmployeeDTOConverter employeeDTOConverter,
                               RandomUserClient randomUserClient) {
        this.employeeRepository = employeeRepository;
        this.employeeDTOConverter = employeeDTOConverter;
        this.randomUserClient = randomUserClient;
    }

    /**
     * Method returns the list of employees and supports sorting and pagination
     *
     * @param pageable object with pagination and sorting information
     * @return list of employees
     */
    public List<EmployeeDTO> getEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable).getContent().stream()
                .map(employeeDTOConverter::convert)
                .collect(Collectors.toList());
    }

    /**
     * Method saves an employee into the DB
     * Part of employee's data comes from the request and part from third-party integration
     *
     * @param createEmployeeRequest object with employee data
     * @return created employee
     * @throws RandomUserException if not enough data from third-party
     */
    public EmployeeDTO saveEmployee(CreateEmployeeRequest createEmployeeRequest) {
        RandomUserDTO randomUserDTO;
        try {
            RandomUserWrapperDTO randomUserWrapper = randomUserClient.getRandomUser();
            randomUserDTO = randomUserWrapper.getResults()[0];
        } catch (Exception e) {
            throw new RandomUserException("There was a problem while accessing downstream system. Please try again later");
        }
        Employee employee = Employee.builder()
                .firstName(randomUserDTO.getName().getFirst())
                .lastName(randomUserDTO.getName().getLast())
                .jobTitle(createEmployeeRequest.getJobTitle())
                .photoURL(randomUserDTO.getPicture().getMedium())
                .build();
        return employeeDTOConverter.convert(employeeRepository.save(employee));
    }

    /**
     * Method updates an existing employee
     *
     * @param id employee's id
     * @param updateEmployeeRequest object with employee data to update
     * @return updated employee record
     * @throws EntityNotFoundException if employee wasn't found in the DB
     */
    public EmployeeDTO updateEmployee(Long id, UpdateEmployeeRequest updateEmployeeRequest) {
        Employee employee = employeeRepository.getOne(id);
        if (!StringUtils.isEmpty(updateEmployeeRequest.getFirstName())) {
            employee.setFirstName(updateEmployeeRequest.getFirstName());
        }
        if (!StringUtils.isEmpty(updateEmployeeRequest.getLastName())) {
            employee.setLastName(updateEmployeeRequest.getLastName());
        }
        if (!StringUtils.isEmpty(updateEmployeeRequest.getJobTitle())) {
            employee.setJobTitle(updateEmployeeRequest.getJobTitle());
        }
        if (!StringUtils.isEmpty(updateEmployeeRequest.getPhotoURL())) {
            employee.setPhotoURL(updateEmployeeRequest.getPhotoURL());
        }
        return employeeDTOConverter.convert(employeeRepository.save(employee));
    }
}
