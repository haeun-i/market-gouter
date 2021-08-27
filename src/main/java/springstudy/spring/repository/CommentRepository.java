package springstudy.spring.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import springstudy.spring.domain.Comment;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class CommentRepository {
    private final EntityManager em;

    public void save(Comment comment){        //코멘트 작성
        if(comment.getId() == null){
            em.persist(comment);
        }
        else{
            em.merge(comment);
        }
    }
    public Comment findById(Long id){     // 레시피 검색
        return em.find(Comment.class, id);
    }
}
