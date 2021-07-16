package com.firstbasehq.employee.management.controller.swagger;

import com.firstbasehq.employee.management.dto.EmployeeDTO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
        value = "Endpoint returns the list of all employees. Can be sorted by id, first/last names, job title",
        response = EmployeeDTO.class,
        responseContainer = "List",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "List of all employees.")
})
@ApiImplicitParams({
        @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                value = "Results page you want to retrieve (0..N)"),
        @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                value = "Number of records per page."),
        @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                value = "Sorting criteria in the format: property(,asc|desc). " +
                        "Default sort order is ascending. " +
                        "Multiple sort criteria are supported.")
})
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface GetEmployees {
}
