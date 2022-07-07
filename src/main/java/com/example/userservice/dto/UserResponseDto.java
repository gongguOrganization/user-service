package com.example.userservice.dto;

import com.example.userservice.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {
    private String userId;
    private String password;

    public static UserResponseDto of(UserEntity member) {
        return UserResponseDto.builder()
                .userId(member.getUserId())
                .password(member.getPassword())
                .build();
    }
}