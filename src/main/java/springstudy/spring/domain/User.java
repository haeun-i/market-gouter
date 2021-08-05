package springstudy.spring.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="user")
@Getter @Setter

public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_num") //pk 설정
    private Long userNum;

    private String userName;

    private Long userId;

    private String userPassword;

    private String userPhone;

    @Embedded //내장타입으로 매핑
    private Address address;

}