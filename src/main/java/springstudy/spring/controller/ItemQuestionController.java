package springstudy.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/itemQuestion/new")
    public String createItemQuestion(Model model){
        List<User> users = userService.findUsers();
        List<Item> items = itemService.findItems();

        model.addAttribute("users", users);
        model.addAttribute("items", items);

        return "items/createItemQuestionForm";
    }

    @PostMapping("itemQuestion/new")
    public String create(ItemQuestion form, @RequestParam("userId") Long userId, @RequestParam("itemId") Long itemId){
        ItemQuestion itemQuestion = new ItemQuestion();
        // AccessLevel 왜 Protected로 했더라?

        itemQuestion.setItemQuestionContent(form.getItemQuestionContent());
        itemQuestion.setItemQuestionDate(form.getItemQuestionDate());

        itemQuestionService.addItemQuestion(itemQuestion, userId, itemId);

        return "redirect:/";
    }


    // update, cancle, read 기능 추가하기




}
