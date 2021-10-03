package springstudy.spring.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name="carts")
public class Cart {
    @Id
    @GeneratedValue
    @Column(name = "cart_id")
    private Long cartId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name = "user_num")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name="count")
    private int cartCount;

    @Column(name="cart_total_price")
    private int cartPrice;

    public static Cart createCart(User user, Item item, int count){

        Cart cart = new Cart();
        int price = item.getItemPrice() * count;

        cart.setUser(user);
        cart.setItem(item);
        cart.setCartCount(count);
        cart.setCartPrice(price);

        return cart;
    }

}
