package kr.co.tmax.bi.web.browser.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import io.micrometer.observation.annotation.Observed;
import kr.co.tmax.bi.web.browser.controller.BrowserController;
import kr.co.tmax.bi.web.browser.dto.UserData;
import kr.co.tmax.bi.web.browser.dto.UserRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;


@Service
@RequiredArgsConstructor
public class UserService implements BrowserController {
    private static Logger LOG = Logger.getLogger(UserService.class.getName());

    private final RestTemplate rest;
    private final EurekaClient eureka;

    @Override
    @Observed(name = "browser.get-user")
    public ResponseEntity<?> getUser(Integer userId) {
        InstanceInfo userRepositoryInstance = eureka.getNextServerFromEureka("USER", false);
        return new ResponseEntity<>(rest.getForObject(userRepositoryInstance.getHomePageUrl()+userId, UserData.class), HttpStatus.OK);
    }

    @Override
    @Observed(name = "browser.add-user")
    public ResponseEntity<?> addUser(UserRegister userRegister) {
        InstanceInfo userRepositoryInstance = eureka.getNextServerFromEureka("USER", false);
        ResponseEntity<Void> response = rest.postForEntity(userRepositoryInstance.getHomePageUrl(), userRegister, Void.class);
        return new ResponseEntity<>(response.getStatusCode());
    }
}
