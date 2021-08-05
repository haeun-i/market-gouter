package springstudy.spring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "item")
@Getter @Setter
public class Item {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String itemName;

    private String itemImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private String itemCategory;

    private String itemQuantity;

    private String itemFrom;

    private String itemIntro;

    private String itemPrice;

    private String itemDescription;

    private List<String> itemOption = new ArrayList<>();

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();
    // DB에 없는데, Item : Order_item 이 '1 : 다' 관계이므로 Item 클래스에 @OneToMany 어노테이션 추가
    // OrderItem DB 생성자는 OrderItem 엔티티 내부에 다음 주석과 같이 작성해야 함.


}