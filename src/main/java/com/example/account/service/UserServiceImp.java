package com.example.account.service;

import com.example.account.domain.Users;
import com.example.account.dto.LoginDto;
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
    @Override
    public ResponseEntity<CustomApiResponse<?>> deleteUser(Long id) {
        Optional<Users> optionalUsers = userRepository.findById(id);
        Users user = optionalUsers.get();
        if (optionalUsers.isPresent()) {
            userRepository.delete(user);
        }
        CustomApiResponse<?> dle = CustomApiResponse.createSuccess(HttpStatus.OK.value(), null, "회원 탈퇴가 성공적으로 진행되었습니다.");
        return ResponseEntity.ok(dle);

    }
    @Override
    public ResponseEntity<CustomApiResponse<?>> loginUser(LoginDto.SDB sdb) {
        Optional<Users> findUser = userRepository.findByUserId(sdb.getUserId());

        if (findUser.isPresent()) {
            Users user = findUser.get();
            // 비밀번호 일치 여부 확인
            if (user.getPassword().equals(sdb.getPassword())) {
                // 비밀번호가 일치하면 로그인 성공 처리
                LoginDto.UpdateUser updateUserResponse = new LoginDto.UpdateUser(user.getUpdatedAt());
                CustomApiResponse<LoginDto.UpdateUser> login = CustomApiResponse.createSuccess(HttpStatus.OK.value(), updateUserResponse, "로그인에 성공하였습니다.");
                return ResponseEntity.ok(login);
            } else {
                // 비밀번호가 일치하지 않으면 에러 메시지 반환
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(CustomApiResponse.createFailWithout(HttpStatus.BAD_REQUEST.value(), "비밀번호가 잘못되었습니다."));
            }
        } else {
            // 사용자 아이디가 존재하지 않으면 에러 메시지 반환
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(CustomApiResponse.createFailWithout(HttpStatus.BAD_REQUEST.value(), "아이디가 잘못되었습니다."));
        }
    }
}
