package springstudy.spring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import springstudy.spring.domain.*;
import springstudy.spring.repository.OrderRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {
    @PersistenceContext EntityManager em;

    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    @Test
    public void 주문실행() throws Exception {
        //Given
        User user = createUser();
        Cart cartA = createCart(user, "사과", "1개", 1);
        Cart cartB = createCart(user, "딸기", "2개", 2);
        Cart cartC = createCart(user, "감자", "3개", 3);

        Long cartIdList[] = {cartA.getCartId(), cartB.getCartId(), cartC.getCartId()};
        Address address = new Address("서울", "강가", "123-123");

        //When
        Long orderId = orderService.createOrder(user.getUserNum(), cartIdList, address,"card");

        //Then
        Order getOrder = orderRepository.findOne(orderId);
        assertEquals("order를 실행 한 user의 ID는", getOrder.getUser().getUserId(), user.getUserId());
        assertEquals("상품 주문시 상태는 ORDER",OrderStatus.ORDER, getOrder.getOrderStatus());

        // 아이템 목록과 배송지 확인 필요
        System.out.println("order 된 item의 목록은");
        System.out.println(getOrder.getOrderItems());
        System.out.println("order 된 배송지는");
        System.out.println(getOrder.getOrderAddress());
    }

    @Test
    public void 주문취소() {
        //Given
        User user = createUser();
        Cart cartA = createCart(user, "사과", "1개", 1);
        Cart cartB = createCart(user, "딸기", "2개", 2);
        Cart cartC = createCart(user, "감자", "3개", 3);

        Long cartIdList[] = {cartA.getCartId(), cartB.getCartId(), cartC.getCartId()};
        Address address = new Address("서울", "강가", "123-123");

        Long orderId = orderService.createOrder(user.getUserNum(), cartIdList, address,"card");

        //When
        orderService.cancelOrder(orderId);

        //Then
        assertNull(orderRepository.findOne(orderId));
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

    private Cart createCart(User user, String itemName, String option, int count){
        Item item = new Item();
        item.setItemName(itemName);
        item.setItemPrice(30000);

        Cart cart = new Cart();
        cart.setCartOption(option);
        cart.setCartCount(count);
        cart.setItem(item);
        cart.setUser(user);

        return cart;
    }
}