package com.example.userservice.dto;

import com.example.userservice.entity.UserEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
@Table(name="member")
public class RegiDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "USER_ID")
    private String userId;
    private String password;
    private String name;
    @Column(name = "POST_CODE")
    private String postcode;
    private String address1;
    private String address2;
    private String address3;
    private String tel;
    private String bank;
    @Column(name = "BANK_ACCOUNT")
    private String bankaccount;

}
