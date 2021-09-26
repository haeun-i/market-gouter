package springstudy.spring.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springstudy.spring.domain.*;
import springstudy.spring.dto.CartDto;
import springstudy.spring.exception.BasicResponse;
import springstudy.spring.exception.CommonResponse;
import springstudy.spring.exception.ErrorResponse;
import springstudy.spring.service.CartService;
import springstudy.spring.service.ItemOptionService;
import springstudy.spring.service.ItemService;
import springstudy.spring.service.UserService;


import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@EnableSwagger2
public class CartController {

    private final UserService userService;
    private final CartService cartService;
    private final ItemService itemService;
    private final ItemOptionService itemOptionService;
    
    @GetMapping(value = "/cart/get") // 장바구니 목록 조회
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", required = true, dataType = "String", paramType = "header")
    })
    public ResponseEntity<? extends BasicResponse> cartList() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        User user = userService.findByUser(id);

        List<CartDto> cartdtos = cartService.findCarts(user);

        return ResponseEntity.ok().body(new CommonResponse<List<CartDto>>(cartdtos));
    }

    @PostMapping(value = "/cart") // 장바구니 추가
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", required = true, dataType = "String", paramType = "header")
    })
    public ResponseEntity<? extends BasicResponse> InsertCart(@RequestParam("itemId") Long itemId,
                        @RequestParam("option") String option,
                        @RequestParam("count") int count) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        User user = userService.findByUser(id);
        Item item = itemService.getItem(itemId);
        List<ItemOption> itemOptions = itemOptionService.getItemOptions(itemId);
        boolean optionCheck = false;
        for(ItemOption optionName : itemOptions){
            System.out.println(optionName.getName());
            if(optionName.getName() == option)  optionCheck = true;
        }

     if(optionCheck == false || count > item.getItemQuantity()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("카트에 아이템을 추가할 수 없습니다."));
        }else{
            Cart cart = cartService.addCart(user.getUserNum(), itemId, option, count);

            CartDto response = new CartDto(cart);
            return ResponseEntity.ok().body(new CommonResponse<CartDto>(response));
        }
    }

    @PutMapping(value = "/cart/option") // 장바구니 옵션 수정
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", required = true, dataType = "String", paramType = "header")
    })
    public ResponseEntity<? extends BasicResponse> ModifyCartOpt(@RequestParam("cartId") Long cartId,
                                @RequestParam("option") String option) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        User user = userService.findByUser(id);
        Cart cart_check = cartService.findCart(cartId);

        Long itemId = cart_check.getItem().getId();
        List<ItemOption> itemOptions = itemOptionService.getItemOptions(itemId);
        boolean optionCheck = false;
        for(ItemOption optionName : itemOptions){
            if(optionName.getName() == option)  optionCheck = true;
        }

        if(cart_check.getUser() != user){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("수정 권한이 없는 사용자입니다."));
        } else if(optionCheck == false){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("사용할 수 없는 option입니다."));
        } else{
            Cart cart = cartService.modifyCartOption(cartId, option);
            CartDto response = new CartDto(cart);
            return ResponseEntity.ok().body(new CommonResponse<CartDto>(response));
        }
    }

    @PutMapping(value = "/cart/count") // 장바구니 수량 수정
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", required = true, dataType = "String", paramType = "header")
    })
    public ResponseEntity<? extends BasicResponse> ModifyCartCount(@RequestParam("cartId") Long cartId,
                                  @RequestParam("count") int count) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        User user = userService.findByUser(id);
        Cart cart_check = cartService.findCart(cartId);
        if(cart_check.getUser() != user){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("수정 권한이 없는 사용자입니다."));
        } else if(cart_check.getItem().getItemQuantity() < count){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("해당 수량으로 상품을 수정할 수 없습니다."));
        } else{
            Cart cart = cartService.modifyCartCount(cartId, count);
            CartDto response = new CartDto(cart);
            return ResponseEntity.ok().body(new CommonResponse<CartDto>(response));
        }
    }

    @PostMapping(value = "/cart/cancel") // 장바구니 삭제
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", required = true, dataType = "String", paramType = "header")
    })
    ResponseEntity<? extends BasicResponse> cancelCart(@RequestParam("cartId") Long cartId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        User user = userService.findByUser(id);
        Cart cart = cartService.findCart(cartId);

        if(cart.getUser() != user){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("삭제 권한이 없는 사용자입니다."));
        }
        else{
            cartService.deleteCart(cartId);
            return ResponseEntity.ok().body(new CommonResponse<String>("delete success"));
        }
    }

}
