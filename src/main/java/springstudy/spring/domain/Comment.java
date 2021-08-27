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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_num")
    private User user;

//    public static Comment createComment(String contents, int recipe_date, int recipe_id, User user){
////        Comment comment = new Comment();
////        comment.SetDate(recipe_date);
////        comment.SetContents(contents);
//    }
}
