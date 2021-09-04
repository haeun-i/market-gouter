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


    private Item item;

    private String category_name;
}