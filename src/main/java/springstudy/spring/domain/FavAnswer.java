package springstudy.spring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "fav_answer")
@Getter @Setter
public class FavAnswer {

    @Id
    @GeneratedValue
    @Column(name = "fav_answer_id")
    private Long id;

    private String favAnswerContent;

    //+ 1:1 양방향
    @OneToOne( fetch = LAZY)
    @JoinColumn(name="fav_question_id")
    private FavQuestion favQuestion;


}
