# likelion-homepage-account

## 회원가입 api , 로그인 api 를 위한 컨트롤러 작성

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

# 각 컨트롤러에서 해당하는 값을 넘기고 저장하기 위한 dto 작성
## signup dto

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
## login dto

public class LoginDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class dto {
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

# 구현제 작성
@Service
@RequiredArgsConstructor
@Builder
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    //회원가입 작성~

    @Override
    public ResponseEntity<CustomApiResponse<?>> saveUser(SignupDto.dto dto){

        // 회원이 존재하는지? -> userId 고유하기때문
        Optional<Users> findUser = userRepository.findByUserId(dto.getUserId());

        //동일한 userId의 경우 회원가입 불가 -> CustomApiResponse create Fail 데이터 반환\
        if(findUser.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(CustomApiResponse.createFailWithout(HttpStatus.BAD_REQUEST.value(), "중복된 아이디 입니다."));

        }
        else{
            Users user = dto.toEntity();
            Users userSave = userRepository.save(user);
        SignupDto.CreateUser createdUserResponse = new SignupDto.CreateUser(userSave.getId(),userSave.getUpdatedAt());
        CustomApiResponse<SignupDto.CreateUser> cus = CustomApiResponse.createSuccess(HttpStatus.OK.value(), null,"회원가입이 완료되었습니다.");
        return ResponseEntity.ok(cus);
        }
    }
    @Override
    public ResponseEntity<CustomApiResponse<?>> deleteUser(String userId) {
        Optional<Users> optionalUsers = userRepository.findByUserId(userId);
        Users user = optionalUsers.get();
        if (optionalUsers.isPresent()) {
            userRepository.delete(user);
            CustomApiResponse<?> dle = CustomApiResponse.createSuccess(HttpStatus.OK.value(), null, "회원 탈퇴가 성공적으로 진행되었습니다.");
            return ResponseEntity.ok(dle);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(CustomApiResponse.createFailWithout(HttpStatus.NOT_FOUND.value(), "존재하지않는 회원입니다."));
        }
    }
    @Override
    public ResponseEntity<CustomApiResponse<?>> loginUser(LoginDto.dto dto
    ) {
        Optional<Users> findUser = userRepository.findByUserId(dto.getUserId());

        if (findUser.isPresent()) {
            Users user = findUser.get();
            // 비밀번호 일치 여부 확인
            if (user.getPassword().equals(dto.getPassword())) {
                // 비밀번호가 일치하면 로그인 성공 처리
                LoginDto.UpdateUser updateUserResponse = new LoginDto.UpdateUser(user.getUpdatedAt());
                CustomApiResponse<LoginDto.UpdateUser> login = CustomApiResponse.createSuccess(HttpStatus.OK.value(), null, "로그인에 성공하였습니다.");
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

# 느낀점
 - 세션시간에 작성하였던 게시글 api를 기반으로 구글링을 하면서 만들다 보니 중간 중간에 데이터 값이 꼬여서 mysql에서 데이터를 확인할때 다른곳에 데이터가 들어가거나 
   값이 지워졌다고 출력되었는데 정작 데이터 베이스에는 데이터가 남아있는경우가 많아서 고생을 많이 했던것 같지만 코드를 수정해가며 
   각각 구조에 대하여 조금 이해할수있는 유익한 시간이였던것 같습니다!

