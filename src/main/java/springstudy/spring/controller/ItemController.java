package springstudy.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
<<<<<<< HEAD
<<<<<<< HEAD
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import springstudy.spring.domain.Item;
import springstudy.spring.dto.ItemDto;
import springstudy.spring.service.ItemService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
 
    @Autowired
    private ItemService itemService;


    // item 들어가면 기 작성된 item 엔티티들이 표시
    @GetMapping("/item")
    public String ItemList(Model model){
        List<ItemDto> itemDtoList = itemService.getItemList();
        model.addAttribute("postList", itemDtoList);
        return "item/list.html";
    } // 넘어가는 itemDtoList들의 속성을 html에서 어떻게 사용하는지 가이드라인 작성 필요

    /* Create */
    @GetMapping("/item/write")
    public String postItem(){
        return "item/postItem.html";
    } // item 작성 페이지로 이동하기

    @PostMapping("/item/write")
    public String writeItem(Item item){
        itemService.saveItem(item);
        return "redirect:/";
    } // 작성 값 넘겨주기

    /* Read One */
    @GetMapping("/item/{id}")
    public String readOne(@PathVariable("id") Long id, Model model){
        ItemDto itemDto = itemService.getItem(id);
        model.addAttribute("post", itemDto);
        return "item/readOne.html";
    } // 그냥 Item으로 수정해야할 듯?

    /* Edit */
    @GetMapping("/item/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model){
        ItemDto itemDto = itemService.getItem(id);
        model.addAttribute("post", itemDto);
        return "item/itemEdit.html";
    }

    /* Delete */
    @GetMapping("/post/{id}")
    public String delete(@PathVariable("id") Long id){
        itemService.deleteItem(id);
        return "redirect:/";
=======
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
>>>>>>> main
=======

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import springstudy.spring.domain.Item;
import springstudy.spring.domain.ItemQuestion;
import springstudy.spring.dto.ItemDto;
import springstudy.spring.service.ItemQuestionService;
import springstudy.spring.service.ItemService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
 
    @Autowired
    private ItemService itemService;
    private ItemQuestionService itemQuestionService;

    // item 들어가면 기작성된 item 엔티티들이 표시
    @GetMapping("/item")
    public String ItemList(Model model){
        List<Item> items = itemService.findItems();
        model.addAttribute("itemList", items);
        return "items/list";
    } // 넘어가는 itemList들의 속성을 html에서 어떻게 사용하는지 가이드라인 작성 필요

    /* Create */
    @GetMapping("/item/write")
    public String postItem(){
        return "items/postItem";
    } // item 작성 페이지로 이동하기

    // 작성 값 넘겨주기
    @PostMapping("/item/write")
    public String writeItem(Item item){
        itemService.saveItem(item);
        return "redirect:/";
    }

    /* Read One */
    @GetMapping("/item/{id}")
    public String readOne(@PathVariable("id") Long id, Model model) {
        Item item = itemService.getItem(id);
        model.addAttribute("item", item);

        // ItemQuestion 전체 목록 조회 기능 추가
        List<ItemQuestion> itemQuestions = itemQuestionService.getItemQuestionList();
        model.addAttribute("itemQuestionList", itemQuestions);

        // Review 전체 목록 조회 기능 추가

        return "items/readOne";
    }

    /* Edit */
    @GetMapping("/item/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model){
        Item item = itemService.getItem(id);
        model.addAttribute("post", item);
        return "items/itemEdit";
    }

    /* Delete */
    @GetMapping("/item/{id}")
    public String delete(@PathVariable("id") Long id){
        itemService.deleteItem(id);
        return "redirect:/";
>>>>>>> main
    }
}