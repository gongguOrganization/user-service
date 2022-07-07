package com.example.userservice.dto;

import com.example.userservice.entity.UserEntity;
import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserRequestDto {
    //Request를 받을 때 쓰이는 dto
    private String userId;
    private String password;


    public UserEntity toMember(PasswordEncoder passwordEncoder) {
        return UserEntity.builder()
                .userId(userId)
                .password(passwordEncoder.encode(password))
                //.nickname(nickname)
                .build();
    }
    //아이디와 비밀번호가 일치하는지 검증
    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(userId, password);
    }
}
