package com.example.userservice.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginDto {

    private String userId;
    private String password;
}
