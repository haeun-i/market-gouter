package springstudy.spring.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="user")
@Getter @Setter

public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_num") //pk 설정
    private Long userNum;

    private String userName;

    private Long userId;

    private String userPassword;

    private String userPhone;

    //2번(수정)
    @Embedded //내장타입으로 매핑
    private Address userAddress;




    //4번 수정 -> OneToOne 같은 경우에 그대로 필드를 가져다 씀, 그 외에 list타입으로
    //우선 전체적으로 양방향으로 가정하고 쓰기
    //(다빈)양방향
    @OneToMany(mappedBy = "user")
    private List<UserQuestion> userQuestions = new ArrayList<>();

    //(유성)
    @OneToMany(mappedBy="user",cascade = CascadeType.ALL)
    private List<Recipe> recipes = new ArrayList<>();

    //(민겸)양방향?
    @OneToMany(mappedBy="user",cascade = CascadeType.ALL)
    private List<ItemQuestion> itemQuestions = new ArrayList<>();

    //(민겸)
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<ItemAnswer> itemAnswers = new ArrayList<>();

    //(민겸)양방향
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Cart cart;

    //(하은)
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    //(유성님 )
    @OneToMany(mappedBy="user",cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    //(민겸님)
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();




}
