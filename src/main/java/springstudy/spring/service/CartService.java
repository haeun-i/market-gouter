package springstudy.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springstudy.spring.domain.Cart;
import springstudy.spring.domain.Item;
import springstudy.spring.domain.OrderItem;
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
    public void addCart(Long userId, Long itemId, String option, int count) {
        User user = userRepository.findOne(userId);
        Item item = itemRepository.findOne(itemId);

        Cart cart = Cart.createCart(user, item, option, count);

        cartRepository.save(cart);
    }

    public void modifyCartCount(Long userId, Long itemId, int count){
        // 변경감지 적용되는지 테스트 필요 -> 트랜잭션 추가
        List<Cart> carts = findCarts(userId);
        for(Cart cart : carts){
            if (cart.getItem().getId() == itemId){
                cart.setCount(count);
            }
        }
    }

    public void modifyCartOption(Long userId, Long itemId, String option){
        // 변경감지 적용되는지 테스트 필요 -> 트랜잭션 추가
        List<Cart> carts = findCarts(userId);
        for(Cart cart : carts){
            if (cart.getItem().getId() == itemId){
                cart.setOption(option);
            }
        }
    }


    @Transactional
    public void deleteCart(Long userId, Long itemId) {
        List<Cart> carts = findCarts(userId);
        for(Cart cart : carts){
            if (cart.getItem().getId() == itemId){
                cartRepository.delete(cart.getId());
            }
        }
    }

    public List<Cart> findCarts(Long memberId){
        return cartRepository.findAll(memberId);
    }

    public Cart findCart(Long cartId){
        return cartRepository.findOne(cartId);
    }

}
