package com.example.account.service;

import com.example.account.domain.Users;
import com.example.account.dto.SignupDto;
import com.example.account.repository.UserRepository;
import com.example.account.util.response.CustomApiResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Builder
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    //회원가입 작성~

    @Override
    public ResponseEntity<CustomApiResponse<?>> saveUser(SignupDto.SDB sdb){

        // 회원이 존재하는지? -> userId 고유하기때문
        Optional<Users> findUser = userRepository.findByUserId(sdb.getUserId());

        //동일한 userId의 경우 회원가입 불가 -> CustomApiResponse create Fail 데이터 반환\
        if(findUser.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(CustomApiResponse.createFailWithout(HttpStatus.BAD_REQUEST.value(), "중복된 아이디 입니다."));

        }
        else{
            Users user = sdb.toEntity();
            Users userSave = userRepository.save(user);
        SignupDto.CreateUser createdUserResponse = new SignupDto.CreateUser(userSave.getId(),userSave.getUpdatedAt());
        CustomApiResponse<SignupDto.CreateUser> cus = CustomApiResponse.createSuccess(HttpStatus.OK.value(), createdUserResponse,"회원가입이 완료되었습니다.");
        return ResponseEntity.ok(cus);
        }
    }


}
