package springstudy.spring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embedded;

@Getter @Setter
public class Comment {

    private Integer comment_id;
    private String comment_contents;
    @Embedded
    private Recipe recipe_id;
    @Embedded
    private User user_id;
}
