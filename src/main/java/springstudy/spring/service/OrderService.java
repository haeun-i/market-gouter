package springstudy.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springstudy.spring.domain.*;
import springstudy.spring.repository.CartRepository;
import springstudy.spring.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public Long createOrder(Long userId, Long[] cartIdList, int count, Address address, String pay) {

        User user = userRepository.findOne(userId);
        List<OrderItem> orderitems = new ArrayList<>();

        Delivery delivery = new Delivery();
        delivery.setAddress(address);
        delivery.setDeliveryStatus(DeliveryStatus.READY);

        Payment payment = new Payment();
        payment.setName(pay);

        for(Long cartId : cartIdList){
            Cart cart = cartRepository.findOne(cartId);
            OrderItem orderItem = OrderItem.createOrderItem(cart.getItem(), cart.getCount(), cart.getOption());
            orderitems.add(orderItem);
        }

        Order order = Order.createOrder(user, delivery, payment, orderitems);

        orderRepository.save(order);

        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.cancelOrder();
        orderRepository.delete(order);
    }

    public List<Order> findOrders(Long userId){
        return orderRepository.findAll(userId);
    }
}
