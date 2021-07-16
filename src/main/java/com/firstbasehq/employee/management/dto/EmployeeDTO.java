package com.firstbasehq.employee.management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Art Yesipovich 7/15/21
 */

@Builder
@AllArgsConstructor
@Data
public class EmployeeDTO {
    private Long id;
    private String jobTitle;
    private String firstName;
    private String lastName;
    private String photoURL;
}
