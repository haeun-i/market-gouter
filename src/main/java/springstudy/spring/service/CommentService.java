package springstudy.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springstudy.spring.domain.Comment;
import springstudy.spring.repository.CommentRepository;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional  // 읽기전용이 아닌 쓰기도 허용하므로
    public Long join(Comment comment){
        commentRepository.save(comment);
        return comment.getId();
    }

    @Transactional
    public void cancelComment(Long commentId){
        Comment comment = commentRepository.findById(commentId);
        comment.cancel();
    }

    public Comment findComment(Long commentId)
    {
        return commentRepository.findById(commentId);
    }
}
