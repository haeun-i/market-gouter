package springstudy.spring.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springstudy.spring.domain.Cart;
import springstudy.spring.domain.User;
import springstudy.spring.exception.BasicResponse;
import springstudy.spring.exception.CommonResponse;
import springstudy.spring.service.CartService;
import springstudy.spring.service.UserService;


import java.util.List;

@Controller
@RequiredArgsConstructor
@EnableSwagger2
public class CartController {

    private final UserService userService;
    private final CartService cartService;
    
    @GetMapping(value = "/cart") // 장바구니 목록 조회
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", required = true, dataType = "String", paramType = "header")
    })
    public ResponseEntity<? extends BasicResponse> orderList(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        User user = userService.findByUser(id);
        List<Cart> carts = cartService.findCarts(user);
        System.out.println(carts);


//        model.addAttribute("carts", carts);
        return ResponseEntity.ok().body(new CommonResponse<List<Cart>>(carts));
    }

//    @PostMapping(value = "/cart") // 장바구니 추가
//    public String InsertCart(@RequestParam("userNum") Long userNum,
//                        @RequestParam("itemId") Long itemId,
//                        @RequestParam("option") String option,
//                        @RequestParam("count") int count) {
//        //cartService.addCart(userNum, itemId, option, count);
//        return "redirect:/item"; // item 페이지에 계속 남아있게 할건지? 카트로 이동시킬 것인지?
//    }

    @PutMapping(value = "/cart/option") // 장바구니 옵션 수정
    public String ModifyCartOpt(@RequestParam("cartId") Long cartId,
                                @RequestParam("option") String option) {
        cartService.modifyCartOption(cartId, option);
        return "redirect:/cart";
    }

    @PutMapping(value = "/cart/count") // 장바구니 수량 수정
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", required = true, dataType = "String", paramType = "header")
    })
    public ResponseEntity<? extends BasicResponse> ModifyCartCount(@RequestParam("cartId") Long cartId,
                                  @RequestParam("count") int count) {
        cartService.modifyCartCount(cartId, count);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/cart/{cartId}/cancel") // 장바구니 삭제
    public String cancelOrder(@PathVariable("cartId") Long cartId) {
        cartService.deleteCart(cartId);
        return "redirect:/cart";
    }

}
