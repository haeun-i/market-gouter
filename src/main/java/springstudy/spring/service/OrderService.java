package springstudy.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springstudy.spring.domain.*;
import springstudy.spring.dto.CartDto;
import springstudy.spring.dto.OrderDto;
import springstudy.spring.repository.CartRepository;
import springstudy.spring.repository.OrderRepository;
import springstudy.spring.repository.PaymentRepository;
import springstudy.spring.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final UserRepository userRepository;
    //private final ItemRepository itemRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    @Transactional
    public Order createOrder(Long userNum, Long[] cartIdList, Address address, Long payId) {

        User user = userRepository.findByUserNum(userNum);
        List<OrderItem> orderItems = new ArrayList<>();

        Payment payment = paymentRepository.findOne(payId);

        int price = 0;

        for(Long cartId : cartIdList){
            Cart cart = cartRepository.findOne(cartId);
            OrderItem orderItem = OrderItem.createOrderItem(cart.getItem(), cart.getCartCount());
            orderItems.add(orderItem);
            price += cart.getCartPrice();

            cartRepository.delete(cartId);
        }

        Order order = Order.createOrder(user, address, payment, orderItems);
        orderRepository.save(order);

        return order;
    }

    @Transactional
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.deleteOrder();
        orderRepository.delete(order);
    }

    @Transactional
    public Order cancelOrder(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.cancelOrder();
        return order;
    }

    public void modifyOrderAddress(Long orderId, String city, String street, String zipcode){
        // 변경감지 적용되는지 테스트 필요 -> 트랜잭션 추가
        Order order = findOrder(orderId);
        Address address = new Address(city, street, zipcode);
        order.setOrderAddress(address);
    }

    public void modifyDeliveryStatus(Long orderId){
        // 변경감지 적용되는지 테스트 필요 -> 트랜잭션 추가
        Order order = findOrder(orderId);
        order.setDeliveryStatus(DeliveryStatus.COMP);
        orderRepository.save(order);
    }


    public List<OrderDto> findOrders(User user){
        List<Order> orders = orderRepository.findAll(user);
        List<OrderDto> orderDtos = new ArrayList<>();
        for(Order order : orders) {
            OrderDto response = new OrderDto(order);
            orderDtos.add(response);
        }
        return orderDtos;
    }

    public Order findOrder(Long orderId){
        return orderRepository.findOne(orderId);
    }
}

