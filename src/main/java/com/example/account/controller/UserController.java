package com.example.account.controller;

import com.example.account.dto.SignupDto;
import com.example.account.service.UserService;
import com.example.account.util.response.CustomApiResponse;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Builder
@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<CustomApiResponse<?>> saveUser(@Valid @RequestBody SignupDto.SDB sdb) {
        //응답
        ResponseEntity<CustomApiResponse<?>> result = userService.saveUser(sdb);
        return result;
    }
//    @PostMapping("/{Id}")
//    public ResponseEntity<CustomApiResponse<?>> deleteUser(
//            @PathVariable("Id")long Id,@Req
//    ) {


  //  }
}
