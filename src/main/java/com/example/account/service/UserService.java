package com.example.account.service;

import com.example.account.dto.LoginDto;
import com.example.account.dto.SignupDto;
import com.example.account.util.response.CustomApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    ResponseEntity<CustomApiResponse<?>> saveUser(SignupDto.@Valid SDB sdb);


    ResponseEntity<CustomApiResponse<?>> deleteUser(Long id);

    ResponseEntity<CustomApiResponse<?>> loginUser(LoginDto.@Valid SDB sdb);
}

