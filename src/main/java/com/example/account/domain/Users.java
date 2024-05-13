package com.example.account.domain;

import com.example.account.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="USERS")
@Builder
public class Users extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "USERS_ID")
    private Long id;
    @Column(name = "USERS_NAME")
    private String username;
    @Column(name = "USERS_PASSWORD")
    private String password;
    @Column(name = "USERS_EMAIL")
    private String email;
    @Column(name = "USERS_PHONENUMBERS")
    private String phonenumbers;
}