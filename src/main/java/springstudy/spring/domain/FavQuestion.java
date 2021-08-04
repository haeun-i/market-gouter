package springstudy.spring.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "fav_question")
@Getter @Setter
public class FavQuestion {

    @Id
    @GeneratedValue
    @Column(name = "fav_question_id")
    private Long id;

    private String favQuestionContent;

    //연관관계 주인x
    //FavQuestion과 FavAnswer 사이
    @OneToOne(mappedBy = "favQuestion", fetch = FetchType.LAZY)
    private FavAnswer favAnswer;

}
