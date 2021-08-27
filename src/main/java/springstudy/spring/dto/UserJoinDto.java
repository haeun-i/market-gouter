package springstudy.spring.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springstudy.spring.domain.Address;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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
        private String userId;

        @NotBlank(message = "6자 이상의 영문 혹은 영문과 숫자를 조합")
        private String userPassword;

        @NotBlank(message = "핸드폰 번호를 입력하시오.")
        @Pattern(regexp="^\\d{3}-\\d{3,4}-\\d{4}$", message = "000-0000-0000")
        private String userPhone;


        //private Address userAddress;


    }

