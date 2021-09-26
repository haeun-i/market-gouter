package springstudy.spring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
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
    @Autowired CartService cartService;
    @Autowired UserService userService;
    @Autowired OrderRepository orderRepository;

    @Test
    @Rollback(false)
    public void 주문실행() throws Exception {
        //Given
        User user = userService.findByNum(2L);
        Cart cartA = cartService.findCart(32L);
        Cart cartB = cartService.findCart(33L);
        Long cartIdList[] = {cartA.getCartId(), cartB.getCartId()};

        Address address = new Address("서울", "강가", "123-123");

        //When
        Order order = orderService.createOrder(user.getUserNum(), cartIdList, address,1L);
        Long orderId = order.getId();

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
    @Rollback(false)
    public void 주문내역삭제() {
        //Given
        Order getOrder = orderService.findOrder(29L);
        Long orderId = getOrder.getId();

        //When
        orderService.deleteOrder(orderId);

        //Then
        assertNull(orderRepository.findOne(orderId));
    }

    @Test
    @Rollback(false)
    public void 주문취소() {
        //Given
        Order getOrder = orderService.findOrder(34L);
        Long orderId = getOrder.getId();

        //When
        orderService.cancelOrder(orderId);

        //Then
        assertNotNull(orderRepository.findOne(orderId));
    }

    @Test
    @Rollback(false)
    public void 주문주소수정() {
        //Given
        Order getOrder = orderService.findOrder(14L);
        Long orderId = getOrder.getId();

        String checkcity = "인천";

        //When
        orderService.modifyOrderAddress(orderId, "인천", "주안", "33333");

        //Then
        assertEquals(getOrder.getOrderAddress().getCity(), checkcity);
    }


    @Test
    @Rollback(false)
    public void 배달상태수정() {
        //Given
        Order getOrder = orderService.findOrder(35L);
        Long orderId = getOrder.getId();

        //When
        orderService.modifyDeliveryStatus(orderId);

        //Then
        System.out.println(getOrder.getDeliveryStatus());
    }

}