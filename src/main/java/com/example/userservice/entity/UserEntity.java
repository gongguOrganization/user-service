package com.example.userservice.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Table(name="member")
@Entity
@Data
@DynamicInsert
public class UserEntity {
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
