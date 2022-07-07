package com.example.userservice.dto;

import lombok.Data;

import javax.persistence.*;


@Data
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
    @Column(name="BANK_ACCOUNT")
    private String bankaccount;
}
