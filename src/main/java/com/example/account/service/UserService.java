package com.example.account.service;

import com.example.account.dto.LoginDto;
import com.example.account.dto.SignupDto;
import com.example.account.util.response.CustomApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


public interface UserService {

    ResponseEntity<CustomApiResponse<?>> saveUser(SignupDto.@Valid dto dto);


    ResponseEntity<CustomApiResponse<?>> deleteUser(String userId);

    ResponseEntity<CustomApiResponse<?>> loginUser(LoginDto.@Valid dto dto);

}

