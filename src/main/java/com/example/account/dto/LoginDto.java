package com.example.account.dto;

import com.example.account.domain.Users;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

public class LoginDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SDB {
        @NotBlank(message = "아이디는 필수값입니다.")
        private String userId;

        @NotBlank(message = "비밀번호는 필수값입니다.")
        private String password;

    }
    @Setter
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UpdateUser {
        private LocalDateTime updatedAt;
        public UpdateUser(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
        }
    }

}
