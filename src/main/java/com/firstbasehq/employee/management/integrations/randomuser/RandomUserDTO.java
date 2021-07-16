package com.firstbasehq.employee.management.integrations.randomuser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Art Yesipovich 7/15/21
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RandomUserDTO {
    private RandomUserNameDTO name;
    private RandomUserPictureDTO picture;
}
