package com.anonymous.mentalcare.service;


import com.anonymous.mentalcare.dto.User.SignupRequestDto;
import com.anonymous.mentalcare.jwt.JwtTokenProvider;
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
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;


    private String secretKey = "hanghae";


    @Transactional
    public String registerUser(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        User sameNameUser = userRepository.findByUserId(username).orElse(null);
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
        // 패스워드 속에 아이디값 중복 없애기
        if (signupRequestDto.getPassword().contains(username) || username.contains(signupRequestDto.getPassword())) {
            errorMessage = "ID을 포함한 비번은 사용불가합니다.";
//            throw new IllegalArgumentException(errorMessage);
            return errorMessage;
        }
        if (!signupRequestDto.getPassword().equals(signupRequestDto.getPasswordCheck())) {
            errorMessage = "비밀번호가 일치하지 않습니다.";
            return errorMessage;
        }
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        // 사용자 ROLE 확인


        User user = new User(username, password);
        userRepository.save(user);
        return errorMessage;
    }
    @Transactional
    public String login(SignupRequestDto requestDto) {
        User user = userRepository.findByUserId(requestDto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 유저입니다."));
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken( user.getUserId(), "");
    }


//    @Transactional
//    public boolean idCheck(String userId){
//        Optional<User> user = userRepository.findByUserId(userId);
//        if(user.isPresent()){
//            return false;
//        }
//        return true;
//    }
    }


