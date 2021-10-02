package springstudy.spring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class ItemOption {
    @Id
    @GeneratedValue
    @Column(name = "option_id")
    private Long id;

    @Column(name = "item_option_name")
    private String name;

    private Long stock; // 재고

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public static ItemOption createItemOption(Item item, String name, Long stock){
        ItemOption itemOption = new ItemOption();
        itemOption.setName(name);
        itemOption.setStock(stock);

        return itemOption;
    }

    // domain에서는 재고 수량 감소만 구현, 실제로 DB에서 빼는 것은 Service에서 구현
    public void cancelOption(int quantity) {
        this.stock -= quantity;
    }
}
