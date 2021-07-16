package com.firstbasehq.employee.management.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Art Yesipovich 7/15/21
 */

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEmployeeRequest {
    private String jobTitle;
    private String firstName;
    private String lastName;
    private String photoURL;
}
