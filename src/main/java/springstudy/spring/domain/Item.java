package springstudy.spring.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "item")
@Getter @Setter
@AllArgsConstructor
@Builder
public class Item {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String itemName;

    @Lob
    @Column(length = 100000)
    private byte[] itemImage;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private CategoryItem categoryItem;

    private String itemQuantity;

    private String itemFrom;

    private String itemIntro;

    private Long itemPrice;

    private String itemDescription;

    private List<String> itemOptions = new ArrayList<>();

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();
    // DB에 없는데, Item : Order_item 이 '1 : 다' 관계이므로 Item 클래스에 @OneToMany 어노테이션 추가
    // OrderItem DB 생성자는 OrderItem 엔티티 내부에 다음 주석과 같이 작성해야 함.

    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
    */

    // Item : ItemQuestion 의 1 : 다 매핑
    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    private List<ItemQuestion> itemQuestions = new ArrayList<>();




}
