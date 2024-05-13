package com.example.account.dto;

import com.example.account.domain.Users;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class SignupDto {

    @NotEmpty(message = "email 은 필수값입니다.")
    @Email(message = "형식에 맞지않는 이메일입니다.")
    private String email;
    @NotEmpty(message = "아이디는 필수값입니다.")
    private String Id;
    @NotEmpty(message = "비밀번호는 필수값입니다.")
    private String password;
    @NotEmpty(message = "이름은 필수값입니다.")
    public String username;
    @NotEmpty(message = "전화번호는 필수값입니다.")
    public String phonenumber;
    public Users toEntity(){
        return Users.builder()
                .email(email)
                .password(password)
                .username(username)
                .phonenumbers(phonenumber)
                .id(Long.valueOf(Id))
                .build();
    }
    public static class CreateUser{
        private Long id;
        private LocalDateTime createdAt;
        public CreateUser(Long id, LocalDateTime createdAt) {
            this.id = id;
            this.createdAt = createdAt;
        }
    }
}