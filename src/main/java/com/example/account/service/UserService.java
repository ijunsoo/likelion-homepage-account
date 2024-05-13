package com.example.account.service;

import com.example.account.domain.Users;
import com.example.account.dto.SignupDto;
import com.example.account.repository.UserRepository;
import com.example.account.util.response.CustomApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    //회원가입 작성~
    public ResponseEntity<CustomApiResponse<?>> signup(SignupDto dto){
        // 회원이 존재하는지? -> userId 고유하기때문
        Optional<Users> findUser = userRepository.findById(Long.valueOf(dto.getId()));

        //동일한 userId의 경우 회원가입 불가 -> CustomApiResponse create Fail 데이터 반환\
        if(findUser.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(CustomApiResponse.createFailWithout(HttpStatus.BAD_REQUEST.value(), "중복된 아이디 입니다."));

        }
        //동일한 userId가 없다 -> 회원가입 진행
        Users   newUser = Users.builder()
                .id(Long.valueOf(dto.getId()))
                .username(dto.getUsername())
                .phonenumbers(dto.getPhonenumber())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
        // 새로운 user 저장
        userRepository.save(newUser);
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(CustomApiResponse.createSuccess(HttpStatus.OK.value(), null,"회원가입에 성공하였습니다."));

    }
}
