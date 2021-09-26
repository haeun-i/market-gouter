package springstudy.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import springstudy.spring.domain.Item;
import springstudy.spring.domain.ItemQuestion;
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
    @GetMapping("/item/post")
    public String postItem(){
        return "items/postItem";
    } // item 작성 페이지로 이동하기

    // 작성 값 넘겨주기
    @PostMapping("/item/write")
    public String writeItem(String name, Long photoId, int quantity, Long categoryId, String from, String intro, int price,
                            String description, Long optionId){
        itemService.createItem(name, photoId, quantity, categoryId, from, intro, price, description, optionId);
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
    @GetMapping("/item/cancel/{id}")
    public String delete(@PathVariable("id") Long id){
        itemService.deleteItem(id);
        return "redirect:/";
    }
}