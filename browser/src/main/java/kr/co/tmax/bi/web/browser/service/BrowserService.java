package kr.co.tmax.bi.web.browser.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import io.micrometer.observation.annotation.Observed;
import kr.co.tmax.bi.web.browser.controller.BrowserController;
import kr.co.tmax.bi.web.browser.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;


@Service
@RequiredArgsConstructor
public class BrowserService implements BrowserController {
    private static Logger LOG = Logger.getLogger(BrowserService.class.getName());

    private final RestTemplate rest;
    private final EurekaClient eureka;

    @Override
    @Observed(name = "browser.get-user")
    public ResponseEntity<?> getUser(Integer userId) {
        InstanceInfo userServiceInstance = eureka.getNextServerFromEureka("USER", false);
        LOG.info(userServiceInstance.getHomePageUrl());
        return new ResponseEntity<>(rest.getForObject(userServiceInstance.getHomePageUrl()+userId, UserDto.class), HttpStatus.OK);
    }

    @Override
    @Observed(name = "browser.add-user")
    public ResponseEntity<?> addUser(UserDto userInfo) {
        InstanceInfo userServiceInstance = eureka.getNextServerFromEureka("USER", false);
        return new ResponseEntity<>(rest.postForObject(userServiceInstance.getHomePageUrl(), userInfo, Void.class), HttpStatus.CREATED);
    }
}
