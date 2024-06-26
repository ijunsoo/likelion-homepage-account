package com.example.account.dto;

import com.example.account.domain.Users;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;



public class SignupDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
public static class dto {
        @NotBlank(message = "email 은 필수값입니다.")
        @Email(message = "형식에 맞지않는 이메일입니다.")
        private String email;

        @NotBlank(message = "아이디는 필수값입니다.")
        private String userId;

        @NotBlank(message = "비밀번호는 필수값입니다.")
        private String password;

        @Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$", message = "10 ~ 11 자리의 숫자만 입력 가능합니다.")
        @NotBlank(message = "전화번호는 필수값입니다.")
        public String phone;

        public Users toEntity() {
            return Users.builder()
                    .userId(userId)
                    .email(email)
                    .password(password)
                    .phone(phone)
                    .build();
        }
    }
    @Getter
    @Builder
    @NoArgsConstructor
    public static class CreateUser{
        private Long id;
        private LocalDateTime createdAt;
        public CreateUser(Long id, LocalDateTime createdAt) {
            this.id = id;
            this.createdAt = createdAt;
        }
    }
}