package springstudy.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springstudy.spring.domain.Cart;
import springstudy.spring.domain.User;
import springstudy.spring.service.CartService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final UserService userService;
    private final CartService cartService;

    @GetMapping(value = "/cart") // 장바구니 목록 조회
    public String orderList(Model model) {
        User user = userServiece.findUser();
        List<Cart> carts = cartService.findCarts(user.getUserNum());
        model.addAttribute("carts", carts);
        return "page/cart";
    }

    @PostMapping(value = "/cart") // 장바구니 추가
    public String InsertCart(@RequestParam("userNum") Long userNum,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("option") String option,
                        @RequestParam("count") int count) {
        cartService.addCart(userNum, itemId, option, count);
        return "redirect:/item"; // item 페이지에 계속 남아있게 할건지? 카트로 이동시킬 것인지?
    }

    @PutMapping(value = "/cart/option") // 장바구니 옵션 수정
    public String ModifyCartOpt(@RequestParam("cartId") Long cartId,
                                @RequestParam("option") String option) {
        cartService.modifyCartOption(cartId, option);
        return "redirect:/cart";
    }

    @PutMapping(value = "/cart/count") // 장바구니 수량 수정
    public String ModifyCartCount(@RequestParam("cartId") Long cartId,
                                  @RequestParam("count") int count) {
        cartService.modifyCartCount(cartId, count);
        return "redirect:/cart";
    }

    @PostMapping(value = "/cart/{cartId}/cancel") // 장바구니 삭제
    public String cancelOrder(@PathVariable("cartId") Long cartId) {
        cartService.deleteCart(cartId);
        return "redirect:/cart";
    }

}
