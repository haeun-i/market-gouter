package springstudy.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springstudy.spring.domain.Order;
import springstudy.spring.service.CartService;
import springstudy.spring.service.OrderService;

import java.util.List;
@Controller
@RequiredArgsConstructor

public class OrderController {

    private final UserService userService;
    private final OrderService orderService;
    private final CartService cartService;


    @GetMapping(value = "/orders") // 주문내역 전체확인
    public String orderList(Model model) {
        User user = userService.findUser();
        List<Order> orders = orderService.findOrders(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("orders", orders);
        return "order/orderList";
    }

    @PostMapping(value = "/order") // 주문 실행
    public String order(@RequestParam("userId") Long userId,
                        @RequestParam("cartIdList") Long[] cartIdList,
                        @RequestParam("count") int count,
                        @RequestParam("address") Address address,
                        @RequestParam("pay") String pay) {
        orderService.createOrder(userId, cartIdList, count, address, pay);
        return "redirect:/orders";
    }


    @PostMapping(value = "/orders/{orderId}/cancel") // 주문 취소
    public String cancelOrder(@PathVariable("orderId") Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }
}
