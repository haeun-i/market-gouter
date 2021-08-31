package springstudy.spring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@Table(name = "Comment")
public class Comment {
    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;
    private String comment_contents;
    private Integer recipe_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
    @Enumerated(EnumType.STRING)    // .origin이 기본, string으로 넣어야 나중에 오류 방지
    private CommentStatus status; // recipe, cancel

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_num")
    private User user;


    public static Comment createComment(String contents, int recipe_date, Recipe recipe_id, User user){
        Comment comment = new Comment();
        comment.setComment_contents(contents);
        comment.setRecipe_date(recipe_date);
        comment.setRecipe(recipe_id);
        comment.setUser(user);
        return comment;
    }

    public void cancel(){
        this.setStatus(CommentStatus.CANCEL);
    }
}
