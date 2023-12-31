package kr.co.tmax.bi.web.user.controller;

import io.micrometer.observation.annotation.Observed;
import kr.co.tmax.bi.web.common.dto.UserData;
import kr.co.tmax.bi.web.common.dto.UserRegister;
import kr.co.tmax.bi.web.user.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public interface UserController {

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    ResponseEntity<UserData> findByUserId(@PathVariable Integer userId);
    @RequestMapping(method = RequestMethod.GET, value = "/email/{email}")
    ResponseEntity<UserData> findByEmail(@PathVariable String email);
    @RequestMapping(method = RequestMethod.POST, value = "/")
    ResponseEntity<Void> insert(@RequestBody UserRegister user);
    @RequestMapping(method = RequestMethod.PUT, value = "/")
    ResponseEntity<Void> updateByUserId(@RequestBody UserData user);

}
