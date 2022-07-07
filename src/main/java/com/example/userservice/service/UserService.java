package com.example.userservice.service;


import com.example.userservice.dto.RegiDto;
import com.example.userservice.dto.TokenDto;
import com.example.userservice.dto.UserRequestDto;

import java.util.Map;

public interface UserService {
    public RegiDto regiUser(RegiDto userRequestDto);

    public RegiDto login(RegiDto regiDto);


    public boolean checkId(String userId);

    Map<String, String> getBankAccount(int id);

}
