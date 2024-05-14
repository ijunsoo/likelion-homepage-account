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
    @Column(name = "ID")
    private long id;

    @Column(name = "USERS_NAME")
    private String username;

    @Column(name = "USERS_ID")
    private String userId;

    @Column(name = "USERS_PASSWORD")
    private String password;

    @Column(name = "USERS_EMAIL")
    private String email;

    @Column(name = "USERS_PHONENUMBER")
    private String phonenumber;

}