package springstudy.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springstudy.spring.domain.Item;
import springstudy.spring.domain.ItemQuestion;
import springstudy.spring.domain.User;
import springstudy.spring.service.ItemQuestionService;
import springstudy.spring.service.ItemService;
import springstudy.spring.service.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemQuestionController {
    private final ItemQuestionService itemQuestionService;
    private final UserService userService;
    private final ItemService itemService;

    // 질문 목록 총 조회는 ItemController의 readOne 함수에서 구현

    /* 질문 작성 */
    @GetMapping("/item/itemQuestion/new")
    public String createItemQuestion(Model model){
        return "items/createItemQuestionForm.html";
    }

    @PostMapping("/item/itemQuestion/new")
    public String writeItemQuestion(ItemQuestion form, @RequestParam("userId") Long userId, @RequestParam("itemId") Long itemId){
        ItemQuestion itemQuestion = new ItemQuestion();

        itemQuestion.setItemQuestionContent(form.getItemQuestionContent());
        itemQuestion.setItemQuestionDate(form.getItemQuestionDate());

        itemQuestionService.addItemQuestion(itemQuestion, userId, itemId);

        return "redirect:/";
    }

    /* 질문 단일 조회 */
    @GetMapping("/item/itemQuestion/{id}")
    public String readOne(@PathVariable("id") Long id, Model model){
        ItemQuestion itemQuestion = itemQuestionService.getItemQuestion(id);
        model.addAttribute("itemQuestion", itemQuestion);

        return "items/itemQuestion";
    } // 질문 답변 조회 기능 추가하기

    /* 질문 수정 */
    @GetMapping("/item/itemQuestion/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model){
        ItemQuestion itemQuestion = itemQuestionService.getItemQuestion(id);
        model.addAttribute("post", itemQuestion);
        return "items/itemQuestionEdit";
    }


    /* 질문 삭제*/
    // 딸려있는 Answer Entity도 함께 삭제되도록 구현?
    @GetMapping("/item/itemQuestion/{id}")
    public String delete(@PathVariable("id")Long id){
        itemQuestionService.deleteItemQuestion(id);
        return "redirect:/";
    }

}