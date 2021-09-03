package springstudy.spring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "category_item")
@Getter @Setter
public class CategoryItem {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String category_name;

    // category_item의 PK와 item 의 1 : 1 매핑
    @OneToOne(mappedBy = "ItemCategory")
    private Item item;
}