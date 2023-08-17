package kr.co.tmax.bi.web.browser.controller;

import kr.co.tmax.bi.web.common.dto.UserData;
import kr.co.tmax.bi.web.common.dto.UserRegister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public interface BrowserController {

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    ResponseEntity<?> getUser(@PathVariable Integer userId);

    @RequestMapping(method = RequestMethod.POST, value = "/")
    ResponseEntity<?> addUser(@RequestBody UserRegister userData);

}
