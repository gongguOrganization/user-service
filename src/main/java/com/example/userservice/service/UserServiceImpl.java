package com.example.userservice.service;

import com.example.userservice.dto.RegiDto;
import com.example.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManagerBuilder managerBuilder;
    @Override
    public RegiDto regiUser(RegiDto regiDto)
    {
        if (userRepository.findByUserId(regiDto.getUserId()) == null) {
            String s_password=passwordEncoder.encode(regiDto.getPassword());
            regiDto.setPassword(s_password);
            return userRepository.save(regiDto);
        } else {
            return null;
        }

    }

    @Override
    public RegiDto login(RegiDto userDto){
        RegiDto user=userRepository.findByUserId(userDto.getUserId());
        String s_password=user.getPassword();
//        System.out.println("s_password"+s_password);
//        System.out.println("r_password"+userDto.getUserId());
        if (passwordEncoder.matches(userDto.getPassword(),s_password)){
            return userRepository.findByUserIdAndPassword( userDto.getUserId(), s_password);
        }
        else{
            return null;
        }
    }

    @Override
    public boolean checkId(String userId) {
        return userRepository.findByUserId(userId) != null;
    }

    @Override
    public Map<String, String> getBankAccount(int id) {
        RegiDto userDto = userRepository.findById(id);
        Map<String, String> bankAccountMap= new HashMap<>(1);
        bankAccountMap.put("bank", userDto.getBank());
        bankAccountMap.put("bankAccount", userDto.getBankaccount());
        bankAccountMap.put("name", userDto.getName());
        return bankAccountMap;
    }

    @Override
    public Boolean checkMyPw(int idAtToken, String password) {
        RegiDto userDto = userRepository.findById(idAtToken);
        //String s_password=passwordEncoder.encode(password);
        //userDto.setPassword(s_password);
//        if (passwordEncoder.matches(userDto.getPassword(),s_password)){
//            return userRepository.findByUserIdAndPassword( userDto.getUserId(), s_password);
//
//        String dbPw = userDto.getPassword();
        return passwordEncoder.matches(password, userDto.getPassword());
    }

    @Override
    public RegiDto getMyInfo(int idAtToken) {
        RegiDto userDto = userRepository.findById(idAtToken);
        userDto.setPassword(null);
        return userDto;
    }

    @Override
    public String getMyPw(int idAtToken) {
        return userRepository.findById(idAtToken).getPassword();
    }

    @Override
    public Boolean updateMyInfo(RegiDto userDto) {
        return userRepository.save(userDto) != null;
    }

    @Override
    public List<RegiDto> getIdList(List<Integer> userIdList) {
        return userRepository.findByIdIn(userIdList);
    }
}
