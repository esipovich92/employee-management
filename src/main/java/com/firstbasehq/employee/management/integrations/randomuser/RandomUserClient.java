package com.firstbasehq.employee.management.integrations.randomuser;

import com.firstbasehq.employee.management.integrations.IntegrationConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Art Yesipovich 7/15/21
 */

@Service
@FeignClient(name = IntegrationConstants.RANDOM_USER_CLIENT_NAME,
             url = "${http.protocol}://" + "${randomuser.hostname}")
public interface RandomUserClient {

    @GetMapping(value = IntegrationConstants.RANDOM_USER_PATH)
    RandomUserWrapperDTO getRandomUser();
}
