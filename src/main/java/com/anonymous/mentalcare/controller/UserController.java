package com.anonymous.mentalcare.controller;

import com.anonymous.mentalcare.dto.User.IdCheckRequestDto;
import com.anonymous.mentalcare.dto.User.IdCheckResponseDto;
import com.anonymous.mentalcare.dto.User.SignupRequestDto;
import com.anonymous.mentalcare.dto.User.UserDetailResponseDto;
import com.anonymous.mentalcare.security.UserDetailsImpl;
import com.anonymous.mentalcare.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<String> registerUser(@RequestBody SignupRequestDto requestDto) {
        userService.registerUser(requestDto);

        return ResponseEntity.ok()
                .body("회원가입 완료!");
    }

    @ApiOperation(value = "로그인 여부 확인")
    @GetMapping("/api/islogin")
    public ResponseEntity<UserDetailResponseDto> isLogin(@AuthenticationPrincipal UserDetailsImpl userDetails){
        System.out.println("islogin proc...");
        System.out.println("islogin request user : " + userDetails.getUser().getUserId());

        UserDetailResponseDto userDetailResponseDto = new UserDetailResponseDto(userDetails.getUsername());

        return ResponseEntity.ok()
                .body(userDetailResponseDto);
    }

    @ApiOperation(value = "아이디 중복 확인")
    @PostMapping("/api/idCheck")
    public ResponseEntity<IdCheckResponseDto> idCheck(@RequestBody IdCheckRequestDto idCheckRequestDto){
        IdCheckResponseDto idCheckResponseDto = userService.idCheck(idCheckRequestDto);

        return ResponseEntity.ok()
                .body(idCheckResponseDto);
    }
}
