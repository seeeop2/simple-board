package com.example.simpleboard.filter.controller;

import com.example.simpleboard.filter.interceptor.OpenApi;
import com.example.simpleboard.filter.model.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/user")
@RestController
public class UserApiController {

    @OpenApi
    @PostMapping("")
    public UserRequest register(@RequestBody UserRequest userRequest){
        log.info("{}",userRequest);
        return userRequest;
    }
    @GetMapping("/hello")
    public void hello(){
        log.info("hello");
    }

}
