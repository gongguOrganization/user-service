package com.example.userservice.controller;

import com.example.userservice.aspect.TokenRequired;
import com.example.userservice.config.SecurityService;
import com.example.userservice.dto.RegiDto;
import com.example.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    SecurityService securityService;

    @Value("${jwt.expTime}")
    long expTime;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody RegiDto regiDto) {
        RegiDto loginUser = userService.login(regiDto);

        String token = securityService.createToken(loginUser.getId().toString());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization","Bearer "+token);
        Map<String,Object> map=new HashMap<>();
        Map<String, Object> headers = new HashMap<>();
        //map.put("token",token);
        map.put("id",loginUser.getId());


        return ResponseEntity.
                ok().
                headers(responseHeaders).
                body(loginUser.getId().toString());
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
    ///////////////MJ
    @PostMapping("/checkPw")
    public Boolean checkMyPw(HttpServletRequest request, @RequestBody RegiDto userDto ){
        return userService.checkMyPw(securityService.getIdAtToken(request), userDto.getPassword());
    }

    @GetMapping("/myInfo")
    public RegiDto getMyInfo(HttpServletRequest request){
        return userService.getMyInfo(securityService.getIdAtToken(request));
    }

    @PutMapping("/updateInfo")
    public Boolean updateMyInfo(HttpServletRequest request, @RequestBody RegiDto userDto){
        userDto.setId(securityService.getIdAtToken(request));

        String pw = userService.getMyPw(securityService.getIdAtToken(request));
        userDto.setPassword(pw);
        return userService.updateMyInfo(userDto);
    }

    @GetMapping("/getIdList")
    public List<RegiDto> getIdList(List<Integer> userIdList){
        return userService.getIdList(userIdList);
    }

}
