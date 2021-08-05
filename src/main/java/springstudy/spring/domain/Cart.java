package springstudy.spring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="delivery")
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue
    @Column(name = "cart_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_num")
    private User user;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_product_id")
    private OrderItem orderItem;


    @Column(name="cart_total_price")
    private int price;

}
