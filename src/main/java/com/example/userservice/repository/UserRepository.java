package com.example.userservice.repository;

import com.example.userservice.dto.RegiDto;
import com.example.userservice.dto.TokenDto;
import com.example.userservice.dto.UserRequestDto;
import com.example.userservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  UserRepository extends JpaRepository<RegiDto,Integer> {
    RegiDto save(RegiDto userRequestDto);

    RegiDto findByUserIdAndPassword(String userId, String password);

    RegiDto findByUserId(String userId);

    RegiDto findById(int id);

    //List<RegiDto> findByIdin(List<Integer> userIdList);
    //비밀번호 체크
}
