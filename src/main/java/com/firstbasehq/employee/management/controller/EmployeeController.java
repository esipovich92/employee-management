package com.firstbasehq.employee.management.controller;

import com.firstbasehq.employee.management.controller.swagger.CreateEmployee;
import com.firstbasehq.employee.management.controller.swagger.GetEmployees;
import com.firstbasehq.employee.management.controller.swagger.UpdateEmployee;
import com.firstbasehq.employee.management.dto.EmployeeDTO;
import com.firstbasehq.employee.management.dto.request.CreateEmployeeRequest;
import com.firstbasehq.employee.management.dto.request.UpdateEmployeeRequest;
import com.firstbasehq.employee.management.service.EmployeeService;
import javax.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author Art Yesipovich 7/15/21
 */

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetEmployees
    @GetMapping
    public List<EmployeeDTO> getEmployees(@ApiIgnore Pageable pageable) {
        return employeeService.getEmployees(pageable);
    }

    @CreateEmployee
    @PostMapping
    public EmployeeDTO createEmployee(@RequestBody @Valid CreateEmployeeRequest createEmployeeRequest) {
        return employeeService.saveEmployee(createEmployeeRequest);
    }

    @UpdateEmployee
    @PutMapping("/{id}")
    public EmployeeDTO updateEmployee(@PathVariable Long id,
                                      @RequestBody UpdateEmployeeRequest updateEmployeeRequest) {
        return employeeService.updateEmployee(id, updateEmployeeRequest);
    }
}
