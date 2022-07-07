package com.example.userservice.service;


import com.example.userservice.dto.RegiDto;

import java.util.List;
import java.util.Map;

public interface UserService {
    public RegiDto regiUser(RegiDto userDto);

    public RegiDto login(RegiDto userDto);


    public boolean checkId(String userId);

    Map<String, String> getBankAccount(int id);

    ////////mj
    Boolean checkMyPw(int idAtToken, String password);

    RegiDto getMyInfo(int idAtToken);

    String getMyPw(int idAtToken);

    Boolean updateMyInfo(RegiDto userDto);

    List<RegiDto> getIdList(List<Integer> userIdList);
}
