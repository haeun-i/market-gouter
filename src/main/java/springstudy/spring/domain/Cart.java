package springstudy.spring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name="count")
    private int count;

    @Column(name="cart_total_price")
    private int price;

    @Column(name="option")
    private String option;

    public static Cart createCart(User user, Item item, String option, int count){

        Cart cart = new Cart();
        //int price = item.getItemPrice();

        cart.setUser(user);
        cart.setItem(item);
        cart.setCount(count);
       // cart.setPrice(price);
        cart.setOption(option);

        return cart;
    }

}
