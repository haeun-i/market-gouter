package springstudy.spring.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import springstudy.spring.domain.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderDto {
    Long orderId;
    Long userNum;
    List<OrderItemDto> orderItems;

    DeliveryStatus deliveryStatus;
    String paymentName;
    LocalDateTime orderDate;
    int orderTotalPrice;
    OrderStatus orderStatus;
    Address orderAddress;

    public OrderDto(Order order){
        orderId = order.getId();
        userNum = order.getUser().getUserNum();

        orderItems = new ArrayList<>();
        for(OrderItem orderitem : order.getOrderItems()) {
            OrderItemDto item = new OrderItemDto(orderitem);
            orderItems.add(item);
        }
        deliveryStatus = order.getDeliveryStatus();
        paymentName = order.getPayment().getName();
        orderDate = order.getOrderDate();
        orderTotalPrice = order.getOrderTotalPrice();
        orderStatus = order.getOrderStatus();
        orderAddress = order.getOrderAddress();
    }
}
