package com.example.userservice.repository;

import com.example.userservice.dto.RegiDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  UserRepository extends JpaRepository<RegiDto,Integer> {
    RegiDto save(RegiDto userDto);

    RegiDto findByUserIdAndPassword(String userId, String password);

    RegiDto findByUserId(String userId);

    RegiDto findById(int id);

    List<RegiDto> findByIdIn(List<Integer> userIdList);

}
