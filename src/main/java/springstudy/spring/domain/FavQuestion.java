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
    @Column(name = "fav_question_id")//P향K
    private Long id;

    private String favQuestionContent;

    //연관관계 주인x, 1:1 양방
    //1번(수정) - cascade는 상태변이가 전파되므로 주체인 favQuestion에서 해야 함.
    @OneToOne(mappedBy = "favQuestion", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private FavAnswer favAnswer;

}
