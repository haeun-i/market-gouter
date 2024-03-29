package springstudy.spring.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
@Getter @Setter
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name = "user_num")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @Column(name="order_date")
    private LocalDateTime orderDate;

    @Column(name = "order_price")
    private int orderTotalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Embedded
    private Address orderAddress;

    public void setUser(User user) {
        this.user = user;
        user.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
        payment.setOrder(this);
    }

    public static Order createOrder(User user, Address address, Payment payment, List<OrderItem> orderItems){
        Order order = new Order();
        order.setUser(user);

        order.setOrderAddress(address);
        for(OrderItem orderItem : orderItems){
            order.addOrderItem(orderItem);
            order.orderTotalPrice += orderItem.getCount() * orderItem.getItem().getItemPrice();
        }
        order.setDeliveryStatus(DeliveryStatus.READY);
        order.setOrderStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        order.setPayment(payment);
        return order;
    }

    public void deleteOrder(){
        if(this.getDeliveryStatus() == DeliveryStatus.COMP){
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }
        this.setOrderStatus(OrderStatus.CANCEL);
        for(OrderItem orderItem : orderItems){
            orderItem.cancelOrderItem();
        }
    }

    public void cancelOrder(){
        if(this.getDeliveryStatus() == DeliveryStatus.COMP){
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }
        this.setOrderStatus(OrderStatus.CANCEL);
        for(OrderItem orderItem : orderItems){
            orderItem.cancelOrderItem();
        }
    }
}
