package com.firstbasehq.employee.management.repository;

import com.firstbasehq.employee.management.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Art Yesipovich 7/15/21
 */

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
