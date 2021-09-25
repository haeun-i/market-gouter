package springstudy.spring.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="user")

public class User implements UserDetails {
    @Id
    @GeneratedValue
    @Column(name = "user_num", nullable = false, unique = true) //pk 설정
    private Long userNum;

    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;

    private String userName;


    @Column(nullable = false)
    private String userPassword;


    private String userPhone;

    //2번(수정)
//    @Embedded //내장타입으로 매핑
//    private Address userAddress;


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

    //(하은)양방향 -> 수정 OneToMany와 ManyToOne 관계
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Cart> carts = new ArrayList<>();

    //(하은)
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Order> orders = new ArrayList<>();

    //(유성님 )
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    //(민겸님)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();


    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    //권한은 회원당 여러개가 세팅될 수 있으므로 collection으로 선언

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}