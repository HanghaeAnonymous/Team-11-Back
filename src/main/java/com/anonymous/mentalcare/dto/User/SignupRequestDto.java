package com.anonymous.mentalcare.dto.User;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class SignupRequestDto {

    @NotBlank(message = "유저명은 필수 입력 값입니다.")
    @Pattern(regexp="^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{3,20}$",
            message = "유저명은 영문자 또는 숫자를 포함해 3자이상 20자 이하 여야 합니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Size(min = 4 , max = 20,
            message = "비밀번호는 4 ~ 20자의 비밀번호여야 합니다.")
    private String password;
    @NotBlank(message = "비밀번호는 확인이 필요합니다.")
    private String passwordCheck;
}
