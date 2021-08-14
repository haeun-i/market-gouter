package springstudy.spring.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springstudy.spring.domain.Address;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
public class UserJoinDto {

    //회원가입에서 클라이언트가 보낸 정보를 전달하는 -DTO
    // 실제 DataBase의 테이블과 1 : 1로 매핑 되는 클래스,
    // DB의 테이블내에 존재하는 컬럼만을 속성(필드)으로 가짐 -Entity



        @NotBlank(message = "이름을 입력하시오")
        private String userName;

        @NotBlank(message = "6자 이상의 영문 혹은 영문과 숫자를 조합")
        @Size(min = 6, message = "6")
        private Long userId;

        @NotBlank(message = "6자 이상의 영문 혹은 영문과 숫자를 조합")
        private String userPassword;
        private String userPhone;
        private Address userAddress;

        @Builder
        public UserJoinDto(Long userId, String userName, String userPassword, String userPhone, Address userAddress) {
            this.userId = userId;
            this.userName = userName;
            this.userPassword = userPassword;
            this.userPhone = userPhone;
        }
    }

