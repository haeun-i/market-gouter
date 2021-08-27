package springstudy.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springstudy.spring.service.CommentService;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/recipes/details")     // 댓글 작성
    public String createForm(Model model){
        model.addAttribute("commentForm", new CommentForm());
        return "redirect:/recipes/details";
    }

    @PostMapping(value = "/recipes/cancel")
    public String cancelComment(@RequestParam("CommentId") Long commentId){
        commentService.cancelComment(commentId);
        return "redirect:/recipes/details";
    }
}
