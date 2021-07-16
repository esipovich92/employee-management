package com.firstbasehq.employee.management.dto.request;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Art Yesipovich 7/15/21
 */

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeRequest {

    @NotEmpty(message = "Job title cannot be empty")
    private String jobTitle;
}
