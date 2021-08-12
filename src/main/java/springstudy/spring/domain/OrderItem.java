package springstudy.spring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="order_item")
@Getter @Setter
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(name = "order_product_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "item_option")
    private String option;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name="item_count")
    private int count;

    @ManyToOne(mappedBy = "cart", fetch = FetchType.LAZY)
    private Cart cart;

    public static OrderItem createOrderItem(Item item, int count, String option){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setCount(count);
        orderItem.setOption(option);

        item.removeStock(count);
        return orderItem;
    }

    public void cancelOrderItem(){
        getItem().addStock(count);
    }
}
