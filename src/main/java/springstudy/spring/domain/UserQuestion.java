package springstudy.spring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_question")
@Getter@Setter
public class UserQuestion{

    @Id @GeneratedValue
    @Column(name = "user_question_id") //pk
    private Long id;

    //다대일 단방향
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_num") //FK
    private User user;


    private String userQuestionContent;

    private LocalDateTime userQuestionDate;

    //연관관계 주인x
    //UserQuestion과 UserAnswer 사이
    @OneToOne(mappedBy = "userQuestion", fetch = FetchType.LAZY)
    private UserAnswer userAnswer;
}
