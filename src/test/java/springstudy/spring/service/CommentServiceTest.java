package springstudy.spring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import springstudy.spring.domain.Comment;
import springstudy.spring.domain.CommentStatus;
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

    @Test
    public void 댓글작성() throws Exception{

        Comment comment = new Comment();
        Long commentId = commentService.join(comment);
        Comment comment1 = commentRepository.findById(commentId);
        assertEquals(CommentStatus.COMMENT, comment1.getStatus());

    }

    public void 댓글삭제() throws Exception{
        Comment comment = new Comment();
        Long commentId = commentService.join(comment);

        commentService.cancelComment(commentId);
        Comment comment1 = commentRepository.findById(commentId);
        assertEquals(CommentStatus.CANCEL, comment1.getStatus());

    }

}