package com.firstbasehq.employee.management.integrations.randomuser;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author Pavel Tsekhanovich
 */
@Data
public class RandomUserException extends RuntimeException {

    private HttpStatus httpStatus;
    private String message;

    public RandomUserException(String msg) {
        super(msg);
        this.message = msg;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
