package kr.co.tmax.bi.web.user.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import io.micrometer.observation.annotation.Observed;
import kr.co.tmax.bi.web.user.controller.UserController;
import kr.co.tmax.bi.web.user.dto.UserDto;
import kr.co.tmax.bi.web.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class UserService implements UserController {
    private Logger LOG = Logger.getLogger(UserService.class.getName());

    private final RestTemplate rest;

    @Override
    @Observed(name = "user.add-user")
    public UserDto getUser(Integer userId) {
        return UserDto.builder().userId(userId).userName("Hello World").build();
    }

    @Override
    @Observed(name = "user.get-user")
    public void addUser(UserDto user) {
        LOG.info(new StringBuilder().append("Called: addUser(UserDto user): user-").append(user).toString());
    }
}
