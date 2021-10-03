package springstudy.spring.dto;

import lombok.Getter;
import lombok.Setter;
import springstudy.spring.domain.Cart;

import javax.persistence.Column;

@Getter
@Setter
public class CartDto {
    Long cartId;
    Long userNum;
    String itemName;
    int cartCount;
    int cartPrice;
    String cartOption;

    public CartDto(Cart cart){
        cartId = cart.getCartId();
        userNum = cart.getUser().getUserNum();
        itemName = cart.getItem().getItemName();

        cartCount = cart.getCartCount();
        cartPrice = cart.getCartPrice();
    }
}
