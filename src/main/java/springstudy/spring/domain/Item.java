package springstudy.spring.domain;

import lombok.*;
import springstudy.spring.exception.NotEnoughStockException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "item")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String itemName;

//    @OneToMany(mappedBy = "item", cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
//            orphanRemoval = true)
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<ItemPhoto> photos = new ArrayList<>();

    private int itemQuantity;

    @OneToMany(mappedBy = "item")
    private List<Cart> carts = new ArrayList<>();

    @OneToOne(mappedBy = "item")
    private CategoryItem categoryItem;

//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private CategoryItem itemCategory;

    private String itemFrom;

    private String itemIntro;

    private int itemPrice;

    private String itemDescription;


    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();
    // DB에 없는데, Item : Order_item 이 '1 : 다' 관계이므로 Item 클래스에 @OneToMany 어노테이션 추가
    // OrderItem DB 생성자는 OrderItem 엔티티 내부에 다음 주석과 같이 작성해야 함.

    // Item : ItemQuestion 의 1 : 다 매핑
    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    private List<ItemQuestion> itemQuestions = new ArrayList<>();


    public void addStock(int quantity){
        this.itemQuantity +=quantity;
    }

    public void removeStock(int quantity) {
        int restStock = this.itemQuantity -= quantity;
        if(restStock < 0){
            throw new NotEnoughStockException("need more stock!");
        }
        this.itemQuantity = restStock;
    }

    @Enumerated(EnumType.STRING)
    private ItemStatus status;

    public void addItemPhoto(ItemPhoto itemPhoto) {
        photos.add(itemPhoto);
        itemPhoto.setItem(this);
    }


    public Item createItem(String name, List<ItemPhoto> photos, int quantity, CategoryItem category, String from, String intro, int price, String description
                           ){
        Item item = new Item();
        item.setItemName(name);
        item.setItemQuantity(quantity);
        item.setCategoryItem(category);
        item.setItemFrom(from);
        item.setItemIntro(intro);
        item.setItemPrice(price);
        item.setItemDescription(description);

        for(ItemPhoto photo: photos){
            item.addItemPhoto(photo);
        }
        this.setStatus(ItemStatus.ITEM);
        return item;
    }

    public void cancel(){
        this.setStatus(ItemStatus.CANCEL);
    }
}