package springstudy.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springstudy.spring.domain.Cart;
import springstudy.spring.domain.Item;
import springstudy.spring.domain.User;
import springstudy.spring.dto.CartDto;
import springstudy.spring.repository.CartRepository;
import springstudy.spring.repository.ItemRepository;

import springstudy.spring.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartService {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    @Autowired
    private final CartRepository cartRepository;

    @Transactional
    public Cart addCart(Long userNum, Long itemId, String option, int count) {
        User user = userRepository.findByUserNum(userNum);
        Item item = itemRepository.findOne(itemId);

        Cart cart = Cart.createCart(user, item, option, count);

        cartRepository.save(cart);
        return cart;
    }

    public Cart modifyCartCount(Long cartId, int count){
        Cart cart = findCart(cartId);
        cart.setCartCount(count);
        cartRepository.save(cart);
        return cart;
    }

    public Cart modifyCartOption(Long cartId, String option){
        // 변경감지 적용되는지 테스트 필요 -> 트랜잭션 추가
        Cart cart = findCart(cartId);
        cart.setCartOption(option);
        cartRepository.save(cart);
        return cart;
    }


    @Transactional
    public void deleteCart(Long cartId) { cartRepository.delete(cartId); }

    public List<CartDto> findCarts(User user){
        List<Cart> carts = cartRepository.findAll(user);
        List<CartDto> cartDtos = new ArrayList<>();
        for(Cart cart : carts) {
            CartDto response = new CartDto(cart);
            cartDtos.add(response);
        }
        return cartDtos;
    }

    public Cart findCart(Long cartId){
        return cartRepository.findOne(cartId);
    }

}
