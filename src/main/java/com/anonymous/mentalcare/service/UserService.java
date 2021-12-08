package com.anonymous.mentalcare.service;


import com.anonymous.mentalcare.dto.User.IdCheckRequestDto;
import com.anonymous.mentalcare.dto.User.IdCheckResponseDto;
import com.anonymous.mentalcare.dto.User.SignupRequestDto;
import com.anonymous.mentalcare.models.User;
import com.anonymous.mentalcare.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public String registerUser(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String errorMessage = "";

        // 회원 ID 중복 확인
        Optional<User> found = userRepository.findByUserId(username);
        if (found.isPresent()) {
            errorMessage = "중복된 사용자 ID 가 존재합니다.";
            return errorMessage;

        }
        Matcher match = Pattern.compile(username).matcher(username);
        if (!match.find()) {
            errorMessage = "닉네임은 숫자와 영문자 조합으로 3~20자리를 사용해야합니다.";
            throw new IllegalArgumentException(errorMessage);
        }

        if (!signupRequestDto.getPassword().equals(signupRequestDto.getPasswordCheck())) {
            errorMessage = "비밀번호가 일치하지 않습니다.";
            return errorMessage;
        }
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        // 사용자 ROLE 확인


        User user = new User(username, password);
        userRepository.save(user);
        System.out.println("회원가입 요청---------");
        System.out.println("username : " + user.getUserId());
        System.out.println("password : " + user.getPassword());
        System.out.println("회원가입 처리 완료-----");
        return errorMessage;
    }

    public IdCheckResponseDto idCheck(IdCheckRequestDto idCheckRequestDto) {
        Optional<User> user = userRepository.findByUserId(idCheckRequestDto.getUsername());
        // isPresent = true 일 때 = 중복, false 출력
        return new IdCheckResponseDto(!user.isPresent());
    }
}
