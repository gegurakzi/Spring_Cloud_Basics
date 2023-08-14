package kr.co.tmax.bi.web.user.controller;

import io.micrometer.observation.annotation.Observed;
import kr.co.tmax.bi.web.user.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface UserController {

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    UserDto getUser(@PathVariable Integer userId);
    @RequestMapping(method = RequestMethod.POST, value = "/")
    void addUser(UserDto user);

}
