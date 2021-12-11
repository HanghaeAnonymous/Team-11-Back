package com.anonymous.mentalcare.service;


import com.anonymous.mentalcare.dto.user.IdCheckRequestDto;
import com.anonymous.mentalcare.dto.user.IdCheckResponseDto;
import com.anonymous.mentalcare.dto.user.SignupRequestDto;
import com.anonymous.mentalcare.models.User;
import com.anonymous.mentalcare.repository.UserRepository;
import com.anonymous.mentalcare.util.LookUpService;
import com.anonymous.mentalcare.util.ValidateChecker;
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
    public void registerUser(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();

        Optional<User> found = userRepository.findByUserId(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }

        // 유효성 검사. 닉네임 3~20자의 대소문자 조합인지 + 비밀번호, 비밀번호확인 일치하는지
        ValidateChecker.registerValidCheck(signupRequestDto);

        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        User user = userRepository.save(new User(username, password));

        // 기록 println 해주는 기능.
        LookUpService.lookUpSignUpProc(user);
    }

    public IdCheckResponseDto idCheck(IdCheckRequestDto idCheckRequestDto) {
        Optional<User> user = userRepository.findByUserId(idCheckRequestDto.getUsername());
        // isPresent = true 일 때 = 중복이므로 가입 불가(false) 출력
        return new IdCheckResponseDto(!user.isPresent());
    }
}
