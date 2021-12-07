package com.anonymous.mentalcare.service;


import com.anonymous.mentalcare.dto.User.SignupRequestDto;
import com.anonymous.mentalcare.jwt.ApiRequestException;
import com.anonymous.mentalcare.jwt.JwtTokenProvider;
import com.anonymous.mentalcare.models.User;
import com.anonymous.mentalcare.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;


    private String secretKey = "hanghae";

    @Transactional
    public void registerUser(SignupRequestDto requestDto) {
        String name = requestDto.getUsername();
        User sameNameUser = userRepository.findByUserId(name).orElse(null);
        if (sameNameUser != null) {
            throw new ApiRequestException("아이디가 중복됩니다.");
        }
        String password;
        password = passwordEncoder.encode(requestDto.getPassword());
        User user = User.builder()
                .userId(name)
                .password(password)
                .build();
        userRepository.save(user);
    }

    @Transactional
    public String login(SignupRequestDto requestDto) {
        System.out.println("오니?");
        User user = userRepository.findByUserId(requestDto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 유저입니다."));
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(user.getUserId(), "");
    }


    @Transactional
    public boolean idCheck(String userId){
        Optional<User> user = userRepository.findByUserId(userId);
        return !user.isPresent();
    }
}
