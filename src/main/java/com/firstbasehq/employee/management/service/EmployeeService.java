package com.firstbasehq.employee.management.service;

import com.firstbasehq.employee.management.dto.EmployeeDTO;
import com.firstbasehq.employee.management.dto.request.CreateEmployeeRequest;
import com.firstbasehq.employee.management.dto.request.UpdateEmployeeRequest;
import java.util.List;
import org.springframework.data.domain.Pageable;

/**
 * @author Art Yesipovich 7/15/21
 */

public interface EmployeeService {
    List<EmployeeDTO> getEmployees(Pageable pageable);
    EmployeeDTO saveEmployee(CreateEmployeeRequest createEmployeeRequest);
    EmployeeDTO updateEmployee(Long id, UpdateEmployeeRequest updateEmployeeRequest);
}
