package springstudy.spring.dto;

import lombok.Getter;
import lombok.Setter;
import springstudy.spring.domain.Item;
import springstudy.spring.domain.Order;
import springstudy.spring.domain.OrderItem;

import javax.persistence.*;

@Getter
@Setter
public class OrderItemDto {

    Long orderItemId;
    String itemName;
    String itemOption;
    int itemCount;
    int itemPrice;

    OrderItemDto(OrderItem orderItem){
        orderItemId = orderItem.getId();
        itemName = orderItem.getItem().getItemName();
        itemCount = orderItem.getCount();
        itemPrice = orderItem.getOrderItem_total_price();
    }
}
