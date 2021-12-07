package com.anonymous.mentalcare.controller;

import com.anonymous.mentalcare.dto.User.SignupRequestDto;
import com.anonymous.mentalcare.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = {"로그인"})
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "기본 회원가입")
    @PostMapping("/api/signup")
    public void registerUser(@RequestBody SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
    }

    @ApiOperation(value = "기본 로그인")
    @PostMapping("/api/login")
    public String login(@RequestBody SignupRequestDto requestDto) {
        return userService.login(requestDto);
    }
}
