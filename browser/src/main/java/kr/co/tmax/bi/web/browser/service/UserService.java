package kr.co.tmax.bi.web.browser.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import io.micrometer.observation.annotation.Observed;
import kr.co.tmax.bi.web.browser.controller.BrowserController;
import kr.co.tmax.bi.web.common.dto.UserData;
import kr.co.tmax.bi.web.common.dto.UserRegister;
import kr.co.tmax.bi.web.common.exception.EmailAlreadyExistsException;
import kr.co.tmax.bi.web.common.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        ResponseEntity<UserData> response = rest.getForEntity(userRepositoryInstance.getHomePageUrl()+userId, UserData.class);
        LOG.info("getUser(): user searched: " + userId);
        if(response.getStatusCode() == HttpStatus.NO_CONTENT) {
            throw new UserNotFoundException(userId);
        }
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);

    }

    @Override
    @Observed(name = "browser.add-user")
    public ResponseEntity<?> addUser(UserRegister userRegister) throws RuntimeException {
        InstanceInfo userRepositoryInstance = eureka.getNextServerFromEureka("USER", false);
        ResponseEntity<UserData> validity = rest.getForEntity(userRepositoryInstance.getHomePageUrl()+"email/"+userRegister.getEmail(), UserData.class);
        LOG.info("addUser(): user searched: " + validity);
        if(validity.getStatusCode() == HttpStatus.NOT_ACCEPTABLE) {
            throw new EmailAlreadyExistsException(userRegister.getEmail());
        }
        ResponseEntity<Void> response = rest.postForEntity(userRepositoryInstance.getHomePageUrl(), userRegister, Void.class);
        LOG.info("addUser(): user created: " + userRegister);
        return new ResponseEntity<>(response.getStatusCode());
    }
}
