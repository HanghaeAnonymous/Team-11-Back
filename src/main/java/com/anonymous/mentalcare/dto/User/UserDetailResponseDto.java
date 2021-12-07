package com.anonymous.mentalcare.dto.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDetailResponseDto {
    private String name;

    @Builder
    public UserDetailResponseDto(String name) {
        this.name = name;

    }
}
