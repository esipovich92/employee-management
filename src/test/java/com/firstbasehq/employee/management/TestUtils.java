package com.firstbasehq.employee.management;

import com.firstbasehq.employee.management.dto.request.CreateEmployeeRequest;
import com.firstbasehq.employee.management.dto.request.UpdateEmployeeRequest;
import com.firstbasehq.employee.management.integrations.randomuser.RandomUserDTO;
import com.firstbasehq.employee.management.integrations.randomuser.RandomUserNameDTO;
import com.firstbasehq.employee.management.integrations.randomuser.RandomUserPictureDTO;
import com.firstbasehq.employee.management.integrations.randomuser.RandomUserWrapperDTO;
import com.firstbasehq.employee.management.model.Employee;

/**
 * @author Art Yesipovich 7/16/21
 */

public class TestUtils {

    public static RandomUserWrapperDTO createRandomUser() {
        RandomUserNameDTO nameDTO = RandomUserNameDTO.builder()
                .first("First Name")
                .last("Last Name")
                .build();
        RandomUserPictureDTO pictureDTO = RandomUserPictureDTO.builder()
                .medium("url")
                .build();
        RandomUserDTO userDTO = RandomUserDTO.builder()
                .name(nameDTO)
                .picture(pictureDTO)
                .build();
        return RandomUserWrapperDTO.builder()
                .results(new RandomUserDTO[]{userDTO})
                .build();

    }

    public static CreateEmployeeRequest buildCreateEmployeeRequest() {
        return CreateEmployeeRequest.builder()
                .jobTitle("Software Engineer")
                .build();
    }

    public static UpdateEmployeeRequest buildUpdateEmployeeRequest() {
        return UpdateEmployeeRequest.builder()
                .firstName("Updated First Name")
                .lastName("Updated Last Name")
                .jobTitle("Updated Job Title")
                .photoURL("Updated Photo URL")
                .build();
    }

    public static Employee buildEmployee() {
        return Employee.builder()
                .id(1L)
                .firstName("First Name")
                .lastName("Last Name")
                .jobTitle("Job Title")
                .photoURL("Photo URL")
                .build();
    }
}
