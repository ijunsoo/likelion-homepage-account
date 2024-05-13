package com.example.account.controller;

import com.example.account.dto.SignupDto;
import com.example.account.service.UserService;
import com.example.account.util.response.CustomApiResponse;
import jakarta.validation.Valid;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Builder
@RestController
@RequestMapping("/api/account")
public class UserController {
    @PostMapping("/signup")
    public ResponseEntity<CustomApiResponse<?>> Signup(@Valid @RequestBody SignupDto dto) {

        //회원가입
        System.out.println(dto.getId());
        System.out.println(dto.getEmail());


        //응답
        ResponseEntity<CustomApiResponse<?>> result = UserServiceService.createPost(req);
        return result;


    }
}
