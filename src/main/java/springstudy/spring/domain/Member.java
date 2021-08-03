package springstudy.spring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "User")
@Getter @Setter // lombok으로 Getter Setter를 모두 연다!
public class Member {

    @Id
    @GeneratedValue // PK로 지정, Sequence 값을 사용
    @Column(name = "member_id")
    private Long id;

    private String name; // 회원명

}