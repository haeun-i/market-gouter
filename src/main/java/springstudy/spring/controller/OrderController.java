package springstudy.spring.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springstudy.spring.domain.*;
import springstudy.spring.dto.CartDto;
import springstudy.spring.dto.OrderDto;
import springstudy.spring.exception.BasicResponse;
import springstudy.spring.exception.CommonResponse;
import springstudy.spring.exception.ErrorResponse;
import springstudy.spring.service.CartService;

import springstudy.spring.service.OrderService;
import springstudy.spring.service.CustomUserDetailService;
import springstudy.spring.service.UserService;


import java.util.List;
@RestController
@RequiredArgsConstructor
public class OrderController {


    private final UserService userService;
    private final OrderService orderService;
    private final CartService cartService;


    @GetMapping(value = "/orders") // 주문내역 전체확인
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", required = true, dataType = "String", paramType = "header")
    })
    public ResponseEntity<? extends BasicResponse> orderList(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();

        User user = userService.findByUser(id);
        List<OrderDto> orderDtos = orderService.findOrders(user);
        return ResponseEntity.ok().body(new CommonResponse<List<OrderDto>>(orderDtos));
    }

    @PostMapping(value = "/order") // 주문 실행
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", required = true, dataType = "String", paramType = "header")
    })
    public ResponseEntity<? extends BasicResponse> order(@RequestParam("cartIdList") Long[] cartIdList,
                        @RequestParam("city") String city,
                        @RequestParam("street") String street,
                        @RequestParam("zipcode") String zipcode,
                        @RequestParam("pay") Long payId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        User user = userService.findByUser(id);
        Address address = new Address(city, street, zipcode);
        Order order = orderService.createOrder(user.getUserNum(), cartIdList, address, payId);
        OrderDto orderdto = new OrderDto(order);
        return ResponseEntity.ok().body(new CommonResponse<OrderDto>(orderdto));
    }


    @PostMapping(value = "/orders/{orderId}/delete") // 주문 삭제
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", required = true, dataType = "String", paramType = "header")
    })
    public ResponseEntity<? extends BasicResponse> deleteOrder(@PathVariable("orderId") Long orderId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        User user = userService.findByUser(id);
        Order order = orderService.findOrder(orderId);

        if(order.getUser() != user){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("삭제 권한이 없는 사용자입니다."));
        }else{
            orderService.deleteOrder(orderId);
            return ResponseEntity.ok().body(new CommonResponse<String>("delete success"));
        }
    }

    @PostMapping(value = "/orders/{orderId}/cancel") // 주문 취소
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", required = true, dataType = "String", paramType = "header")
    })
    public ResponseEntity<? extends BasicResponse> cancelOrder(@PathVariable("orderId") Long orderId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        User user = userService.findByUser(id);
        Order order = orderService.findOrder(orderId);

        if(order.getUser() != user){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("삭제 권한이 없는 사용자입니다."));
        }else{
            orderService.cancelOrder(orderId);
            return ResponseEntity.ok().body(new CommonResponse<String>("delete success"));
        }
    }

    @PutMapping(value = "/orders/{orderId}/delivery") // 배송한 상태로 수정
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", required = true, dataType = "String", paramType = "header")
    })
    public ResponseEntity<? extends BasicResponse> modifyDelivery(@PathVariable("orderId") Long orderId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        User user = userService.findByUser(id);
        orderService.modifyDeliveryStatus(orderId);

        Order order = orderService.findOrder(orderId);
        OrderDto orderdto = new OrderDto(order);
        return ResponseEntity.ok().body(new CommonResponse<OrderDto>(orderdto));
    }
}