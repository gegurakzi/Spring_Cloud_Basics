package kr.co.tmax.bi.web.browser.controller;

import io.micrometer.observation.annotation.Observed;
import kr.co.tmax.bi.web.browser.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface BrowserController {

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    ResponseEntity<?> getUser(@PathVariable Integer userId);

    @RequestMapping(method = RequestMethod.POST, value = "/")
    ResponseEntity<?> addUser(UserDto userInfo);

}
