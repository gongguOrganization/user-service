package com.example.userservice.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    //SecurityContext에 유저 정보가 저장되는 시점을 다루는 클래스
    private SecurityUtil() { }

    public static Integer getCurrentMemberId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getName() == null) {
            throw new RuntimeException("Security Context에 인증 정보가 없습니다.");
        }

        return Integer.parseInt(authentication.getName());
    }
}
