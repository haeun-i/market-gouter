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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name="item_count")
    private int count;

    @Column(name="item_total_price")
    private int orderItem_total_price;

    public static OrderItem createOrderItem(Item item, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setCount(count);
        orderItem.setOrderItem_total_price(item.getItemPrice() * count);
        item.removeStock(count);

        return orderItem;
    }

    public void cancelOrderItem(){
        getItem().addStock(count);
    }
}
