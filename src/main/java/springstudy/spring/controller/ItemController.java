package springstudy.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import springstudy.spring.domain.Item;
import springstudy.spring.service.ItemService;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    // 판매 게시글 작성 페이지
    @GetMapping("/items/new")
    public String createForm(Model model){
        model.addAttribute("form", new Item());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(Item form, Long CategoryItemId){
        Item item = new Item();

        item.setItemName(form.getItemName());
        item.setItemImage(form.getItemImage());
        item.setItemQuantity(form.getItemQuantity());
        item.setItemFrom(form.getItemFrom());
        item.setItemIntro(form.getItemIntro());
        item.setItemPrice(form.getItemPrice());
        item.setItemDescription(form.getItemDescription());

        item.setItemOptions(form.getItemOptions());

        itemService.saveItem(item, CategoryItemId);
        return "redirect:/";
    }

    // 판매 게시글 수정 메소드
    @GetMapping("items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model){
        Item item = itemService.findOne(itemId);
        // 왜 기존에 존재하는 item으로 form 객체를 초기화하는지?

        Item form = new Item(); // 새로운 Item 객체를 생성하고
        form.setItemName(item.getItemName());
        form.setItemImage(item.getItemImage());
        form.setItemQuantity(item.getItemQuantity());
        form.setItemFrom(item.getItemFrom());
        form.setItemIntro(item.getItemIntro());
        form.setItemPrice(item.getItemPrice());
        form.setItemDescription(item.getItemDescription());

        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

    @PostMapping("items/{itemId}/edit")
    public String updateItem(@ModelAttribute("form") Item form){
        Item item = new Item();
        item.setItemName(form.getItemName());
        item.setItemImage(form.getItemImage());
        item.setItemQuantity(form.getItemQuantity());
        item.setItemFrom(form.getItemFrom());
        item.setItemIntro(form.getItemIntro());
        item.setItemPrice(form.getItemPrice());
        item.setItemDescription(form.getItemDescription());

        itemService.updateItem(form.getId(), item);
        return "redirect:/items";
    }
}