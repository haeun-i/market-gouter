package springstudy.spring.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Item_question")
@Getter @Setter
@NoArgsConstructor
public class ItemQuestion {
    @Id @GeneratedValue
    @Column(name = "item_question_id")
    private Long id; // PK

    private String itemQuestionContent;

    private LocalDateTime itemQuestionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_num")
    private User user; // user_id FK

    // ItemQuestion과 item의 다 : 1 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    //  ItemQuestion은 ItemAnswer과의 연관관계의 주인이 아님.
    @OneToOne(mappedBy = "itemQuestion", fetch = FetchType.LAZY)
    private ItemAnswer itemAnswer;

}
