package com.firstbasehq.employee.management.dto.converter;

import com.firstbasehq.employee.management.dto.EmployeeDTO;
import com.firstbasehq.employee.management.model.Employee;
import org.springframework.stereotype.Component;

/**
 * @author Art Yesipovich 7/15/21
 */

@Component
public class EmployeeDTOConverter implements DTOConverter<Employee, EmployeeDTO> {

    @Override
    public EmployeeDTO convert(Employee employee) {
        return EmployeeDTO.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .jobTitle(employee.getJobTitle())
                .photoURL(employee.getPhotoURL())
                .build();
    }
}
