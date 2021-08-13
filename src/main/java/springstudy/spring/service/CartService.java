package springstudy.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springstudy.spring.domain.Cart;
import springstudy.spring.domain.Item;
import springstudy.spring.domain.OrderItem;
import springstudy.spring.domain.User;
import springstudy.spring.repository.CartRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartService {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    @Transactional
    public void addCart(Long userNum, Long itemId, String option, int count) {
        User user = userRepository.findOne(userNum);
        Item item = itemRepository.findOne(itemId);

        Cart cart = Cart.createCart(user, item, option, count);

        cartRepository.save(cart);
    }

    public void modifyCartCount(Long cartId, int count){
        // 변경감지 적용되는지 테스트 필요 -> 트랜잭션 추가
        Cart cart = findCart(cartId);
        cart.setCount(count);
    }

    public void modifyCartOption(Long cartId, String option){
        // 변경감지 적용되는지 테스트 필요 -> 트랜잭션 추가
        Cart cart = findCart(cartId);
        cart.setOption(option);
    }


    @Transactional
    public void deleteCart(Long cartId) { cartRepository.delete(cartId); }

    public List<Cart> findCarts(Long userNum){
        return cartRepository.findAll(userNum);
    }

    public Cart findCart(Long cartId){
        return cartRepository.findOne(cartId);
    }

}
