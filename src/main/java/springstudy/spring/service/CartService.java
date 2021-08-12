package springstudy.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springstudy.spring.domain.Cart;
import springstudy.spring.domain.Item;
import springstudy.spring.repository.CartRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartService {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    //==장바구니 추가==//
    @Transactional
    public void addCart(Long userId, Long itemId, String option, int count) {
        User user = userRepository.findOne(userId);
        Item item = itemRepository.findOne(itemId);

        Cart cart = Cart.createCart(user, item, count, option);

        cartRepository.save(cart);
    }

    @Transactional
    public void deleteCart(Long cartId) {
        cartRepository.delete(cartId);
    }

    public List<Cart> Cartlist(Long memberId){
        return cartRepository.findAll(memberId);
    }

    public Cart findCart(Long cartId){
        return cartRepository.findOne(cartId);
    }

}
