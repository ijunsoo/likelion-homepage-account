package com.example.account.controller;

import com.example.account.dto.LoginDto;
import com.example.account.dto.SignupDto;
import com.example.account.service.UserService;
import com.example.account.util.response.CustomApiResponse;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<CustomApiResponse<?>> saveUser(@Valid @RequestBody SignupDto.dto dto) {
        //응답
        ResponseEntity<CustomApiResponse<?>> result = userService.saveUser(dto);
        return result;
    }
    @DeleteMapping("/withdraw/{usrId}")
    public ResponseEntity<CustomApiResponse<?>> deleteUser(
            @PathVariable("userId")String userId
            ) {
        ResponseEntity<CustomApiResponse<?>> result = userService.deleteUser(userId);
        return result;
    }
    @PostMapping("/login")
    public ResponseEntity<CustomApiResponse<?>> loginUser(@Valid@RequestBody LoginDto.dto dto){
        ResponseEntity<CustomApiResponse<?>> result = userService.loginUser(dto);
        return result;
    }

}
