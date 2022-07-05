package com.example.userservice.service;

import com.example.userservice.dto.LoginDto;
import com.example.userservice.dto.RegiDto;
import com.example.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public RegiDto regiUser(RegiDto userDto)
    {
        System.out.println("service >>>> ");
        System.out.println(userDto);
        if(userRepository.findByUserId(userDto.getUserId())==null ){

            return userRepository.save(userDto);
        } else {
            return null;
        }

    }

    @Override
    public RegiDto login(RegiDto userDto){

        return userRepository.findByUserIdAndPassword( userDto.getUserId(), userDto.getPassword());

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
}
