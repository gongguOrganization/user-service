package com.example.userservice.controller;

import com.example.userservice.aspect.TokenRequired;
import com.example.userservice.config.SecurityService;
import com.example.userservice.dto.LoginDto;
import com.example.userservice.dto.RegiDto;
import com.example.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    SecurityService securityService;
    @PostMapping("/login")
    public Map login(@RequestBody RegiDto userDto) {
        RegiDto loginUser = userService.login(userDto);
        String token = securityService.createToken(loginUser.getId().toString());
        Map<String,Object> map=new HashMap<>();
        map.put("token",token);
        map.put("id",loginUser.getId());
        return map;
    }

    @GetMapping("/token")
    @TokenRequired
    public String getToken()
    {
        ServletRequestAttributes requestAttributes=(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request=requestAttributes.getRequest();

        String tokenBearer=request.getHeader("Authorization");

        String subject=securityService.getSubject(tokenBearer);
        return  subject;
    }

    @PostMapping("/join")
    public RegiDto createUser(@RequestBody RegiDto userDto) {
        return userService.regiUser(userDto);
    }

    @GetMapping("/checkId/{userId}")
    public boolean checkId(@PathVariable String userId) {
        return userService.checkId(userId);
    }
}
