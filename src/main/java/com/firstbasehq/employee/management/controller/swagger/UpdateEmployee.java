package com.firstbasehq.employee.management.controller.swagger;

import com.firstbasehq.employee.management.dto.EmployeeDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.http.MediaType;

/**
 * @author Art Yesipovich 7/15/21
 */

@ApiOperation(
        value = "Updates an employee.",
        response = EmployeeDTO.class,
        produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Employee was successfully updated.")
})
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UpdateEmployee {
}
