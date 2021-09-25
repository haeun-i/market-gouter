package springstudy.spring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import springstudy.spring.domain.Comment;
import springstudy.spring.domain.CommentStatus;
import springstudy.spring.domain.Recipe;
import springstudy.spring.domain.User;
import springstudy.spring.repository.CommentRepository;
import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CommentServiceTest {

    @Autowired EntityManager em;
    @Autowired CommentService commentService;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserService userService;
    @Autowired
    RecipeService recipeService;


    @Test
    public void 댓글작성() throws Exception{
        User user = userService.findByNum(2L);
        String content = "wow";
        int date = 20;
        Recipe recipe = recipeService.findOne(10L);
        Comment comment = Comment.createComment(content, date, recipe, user);
        Long commentId = commentService.join(comment);
        Comment comment1 = commentRepository.findById(commentId);
        assertEquals(CommentStatus.COMMENT, comment1.getStatus());
    }

    public void 댓글삭제() throws Exception{
        Comment comment = commentService.findComment(5L);
        Long commentId = commentService.join(comment);

        commentService.cancelComment(commentId);
        Comment comment1 = commentRepository.findById(commentId);
        assertEquals(CommentStatus.CANCEL, comment1.getStatus());
    }

}