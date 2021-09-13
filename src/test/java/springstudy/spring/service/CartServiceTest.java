package springstudy.spring.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import springstudy.spring.domain.Address;
import springstudy.spring.domain.Cart;
import springstudy.spring.domain.Item;
import springstudy.spring.domain.User;
import springstudy.spring.repository.CartRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class CartServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    CartService cartService;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserService userService;
    @Autowired
    ItemService itemService;


    @Test
    @Rollback(false)
    public void 상품추가() throws Exception {
        //Given
        User user = userService.findByNum(1L);
        Item item = itemService.getItem(1L);
        String option = "100g";
        int count = 3;

        //When
        Long cartId = cartService.addCart(user.getUserNum(), item.getId(), option, count);

        //Then
        Cart getCart = cartRepository.findOne(cartId);
        //System.out.println(getCart.getUser().getUsername());
        assertEquals(getCart.getUser().getUserId(), user.getUserId());
        assertEquals(getCart.getItem().getItemName(), item.getItemName());
        assertEquals(getCart.getCartOption(), option);
        // option, 수량 확인 필요
        System.out.println("cart에 들어가있는 item의 수량은");
        System.out.println(getCart.getCartCount());
    }

    @Test
    @Rollback(false)
    public void 상품취소() {
        //Given
        Cart cart = cartService.findCart(10L);
        // 이게 디비에 있는 카트를 기준으로 확인해보려고
        // ID에 그냥 DB에 있는 카트 아이디 아무거나 찾아서 넣었습니다
        // 나중에 진짜 서비스에서는 아이디 가져와서 넣어서 쓰면 될 것 같아요!
        Long cartId = cart.getCartId();

        //When
        cartService.deleteCart(cartId);

        //Then
        assertNull(cartRepository.findOne(cartId));
    }

    @Test
    @Rollback(false)
    public void 상품수량변경() {
        //Given
        Cart cart = cartService.findCart(10L);
        // 이게 디비에 있는 카트를 기준으로 확인해보려고
        // ID에 그냥 DB에 있는 카트 아이디 아무거나 찾아서 넣었습니다
        // 나중에 진짜 서비스에서는 아이디 가져와서 넣어서 쓰면 될 것 같아요!
        Long cartId = cart.getCartId();

        //When
        cartService.modifyCartCount(cartId, 5);

        //Then
        Cart getCart = cartRepository.findOne(cartId);
        System.out.println("cart에 들어가있는 item의 수량은");
        System.out.println(getCart.getCartCount());
    }

    @Test
    @Rollback(false)
    public void 상품옵션변경() {
        //Given
        Cart cart = cartService.findCart(10L);
        // 이게 디비에 있는 카트를 기준으로 확인해보려고
        // ID에 그냥 DB에 있는 카트 아이디 아무거나 찾아서 넣었습니다
        // 나중에 진짜 서비스에서는 아이디 가져와서 넣어서 쓰면 될 것 같아요!
        Long cartId = cart.getCartId();

        //When
        String modifyOption = "200g";
        cartService.modifyCartOption(cartId, "200g");

        //Then
        Cart getCart = cartRepository.findOne(cartId);
        assertEquals(getCart.getCartOption(), modifyOption);
    }

}