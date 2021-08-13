package springstudy.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springstudy.spring.domain.Cart;
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
        List<Cart> carts = cartService.findCarts(user.getId());
        model.addAttribute("carts", carts);
        return "page/cart";
    }

    @PostMapping(value = "/cart") // 장바구니 추가
    public String Cart(@RequestParam("userId") Long userId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("option") String option,
                        @RequestParam("count") int count) {
        cartService.addCart(userId, itemId, option, count);
        return "redirect:/item"; // item 페이지에 계속 남아있게 할건지? 카트로 이동시킬 것인지?
    }

    @PutMapping(value = "/cart/option") // 장바구니 옵션 수정
    public String order(@RequestParam("userId") Long userId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("option") String option) {
        cartService.modifyCartOption(userId, itemId, option);
        return "redirect:/cart";
    }

    @PutMapping(value = "/cart/count") // 장바구니 수량 수정
    public String order(@RequestParam("userId") Long userId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count) {
        cartService.modifyCartCount(userId, itemId, count);
        return "redirect:/cart";
    }

    @PostMapping(value = "/cart/{itemId}/cancel") // 장바구니 삭제
    public String cancelOrder(@PathVariable("itemId") Long itemId,
                              @RequestParam("userId") Long userId) {
        cartService.deleteCart(userId, itemId);
        return "redirect:/cart";
    }

}
