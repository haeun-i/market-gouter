package springstudy.spring.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springstudy.spring.domain.Address;
import springstudy.spring.domain.Cart;
import springstudy.spring.domain.Order;
import springstudy.spring.domain.User;
import springstudy.spring.service.CartService;

import springstudy.spring.service.OrderService;
import springstudy.spring.service.CustomUserDetailService;
import springstudy.spring.service.UserService;


import java.util.List;
@Controller
@RequiredArgsConstructor
public class OrderController {


    private final UserService userService;
    private final OrderService orderService;
    private final CartService cartService;


    @GetMapping(value = "/orders") // 주문내역 전체확인
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", required = true, dataType = "String", paramType = "header")
    })
    public String orderList(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();

        User user = userService.findByUser(id);
        List<Order> orders = orderService.findOrders(user);
        return "order/orderList";
    }

    @PostMapping(value = "/order") // 주문 실행
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", required = true, dataType = "String", paramType = "header")
    })
    public String order(@RequestParam("cartIdList") Long[] cartIdList,
                        @RequestParam("address") Address address,
                        @RequestParam("pay") Long payId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();

        User user = userService.findByUser(id);
        orderService.createOrder(user.getUserNum(), cartIdList, address, payId);
        return "redirect:/orders";
    }


    @PostMapping(value = "/orders/{orderId}/delete") // 주문 삭제
    public String deleteOrder(@PathVariable("orderId") Long orderId) {
        orderService.deleteOrder(orderId);
        return "redirect:/orders";
    }

    @PostMapping(value = "/orders/{orderId}/cancel") // 주문 취소
    public String cancelOrder(@PathVariable("orderId") Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }
}