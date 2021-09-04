package springstudy.spring.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

    @PersistenceContext EntityManager em;

    @Autowired CartService cartService;
    @Autowired CartRepository cartRepository;

    @Test
    public void 상품추가() throws Exception {
        //Given
        User user = createUser();
        Item item = createItem();
        String option = "100g";
        int count = 3;

        //When
        Long cartId = cartService.addCart(user.getUserNum(), item.getId(), option, count);

        //Then
        Cart getCart = cartRepository.findOne(cartId);
        assertEquals("cart가 들어가있는 user의 ID는", getCart.getUser().getUserId(), user.getUserId());
        assertEquals("cart에 들어가있는 item의 이름은", getCart.getItem().getItemName(), item.getItemName());
        assertEquals("cart에 들어가있는 item의 옵션은", getCart.getOption(), option);
        // option, 수량 확인 필요
        System.out.println("cart에 들어가있는 item의 수량은");
        System.out.println(getCart.getCount());
    }

    @Test
    public void 상품취소() {
        //Given
        User user = createUser();
        Item item = createItem();
        String option = "100g";
        int count = 3;
        Long cartId = cartService.addCart(user.getUserNum(), item.getId(), option, count);

        //When
        cartService.deleteCart(cartId);

        //Then
        assertNull(cartRepository.findOne(cartId));
    }

    @Test
    public void 상품수량변경() {
        //Given
        User user = createUser();
        Item item = createItem();
        String option = "100g";
        int count = 3;
        Long cartId = cartService.addCart(user.getUserNum(), item.getId(), option, count);

        //When
        cartService.modifyCartCount(cartId, 5);

        //Then
        Cart getCart = cartRepository.findOne(cartId);
        System.out.println("cart에 들어가있는 item의 수량은");
        System.out.println(getCart.getCount());
    }

    @Test
    public void 상품옵션변경() {
        //Given
        User user = createUser();
        Item item = createItem();
        String option = "100g";
        int count = 3;
        Long cartId = cartService.addCart(user.getUserNum(), item.getId(), option, count);

        //When
        String modifyOption = "200g";
        cartService.modifyCartOption(cartId, "200g");

        //Then
        Cart getCart = cartRepository.findOne(cartId);
        assertEquals("cart에 들어가있는 item의 이름은", getCart.getOption(), modifyOption);
    }

    private User createUser() {
        User user = new User();
        user.setUserId("nueahx7674");
        user.setUserName("haeun");
        user.setUserPhone("010-5917-9098");
        user.setUserPassword("qwerty");
        user.setUserAddress(new Address("서울", "강가", "123-123"));

        em.persist(user);
        return user;
    }

    private Item createItem(){
        Item item = new Item();
        item.setItemName("apple");
        item.setItemPrice(30000);

        em.persist(item);
        return item;
    }
}