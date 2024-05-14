package com.example.account.service;

import com.example.account.domain.Users;
import com.example.account.dto.SignupDto;
import com.example.account.repository.UserRepository;
import com.example.account.util.response.CustomApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

    ResponseEntity<CustomApiResponse<?>> saveUser(SignupDto.@Valid SDB sdb);
}

