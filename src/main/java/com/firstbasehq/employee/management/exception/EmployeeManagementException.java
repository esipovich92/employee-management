package com.firstbasehq.employee.management.exception;

import lombok.Builder;
import lombok.Data;

/**
 * @author Art Yesipovich 7/15/21
 */

@Builder
@Data
public class EmployeeManagementException {
    private String message;

    public static EmployeeManagementException buildException(String message) {
        return EmployeeManagementException.builder()
                .message(message)
                .build();
    }
}
