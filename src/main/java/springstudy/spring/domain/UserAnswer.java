package springstudy.spring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "user_answer")
@Getter @Setter
public class UserAnswer {

    @Id
    @GeneratedValue
    @Column(name = "user_answer_id")
    private Long id;  //PK

    private String userAnswerContent;

    //해당시간
    private LocalDateTime userAnswerDate;


    //1:1 양방향관계
    @OneToOne( fetch = LAZY)
    @JoinColumn(name = "user_question_id")
    private UserQuestion userQuestion;


}