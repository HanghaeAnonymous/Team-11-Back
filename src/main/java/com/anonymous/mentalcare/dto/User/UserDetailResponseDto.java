package com.anonymous.mentalcare.dto.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDetailResponseDto {
    private UserInfoDto userInfo;


    @Builder
    public UserDetailResponseDto(String username) {
        this.userInfo = new UserInfoDto(username);
    }
}
