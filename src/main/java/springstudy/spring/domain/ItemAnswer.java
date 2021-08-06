package springstudy.spring.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "itme_answer")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemAnswer {
    @Id @GeneratedValue
    @Column("item_answer_id")
    private Long id;

    private String itemAnswerContent;

    private LocalDateTime itemAnswerDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_num")
    private User user;

    // OneToOne 연관관계의 주인인 ItemAnswer에서 JoinColumn 어노테이션 추가.
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_question_id")
    private ItemQuestion itemQuestion;

}
