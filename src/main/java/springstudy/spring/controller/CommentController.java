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

    @PostMapping("/comment")     // 댓글 작성
    public String createForm(Model model){
        model.addAttribute("commentForm", new CommentForm());
        return "redirect:/comment";
    }

    @PostMapping(value = "/commentcancel")
    public String cancelComment(@RequestParam("CommentId") Long commentId){
        commentService.cancelComment(commentId);
        return "redirect:/comment";
    }
}
