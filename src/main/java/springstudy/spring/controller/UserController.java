package springstudy.spring.controller;


import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import springstudy.spring.domain.User;
import springstudy.spring.dto.LoginDto;
import springstudy.spring.dto.UserJoinDto;
import springstudy.spring.exception.BasicResponse;
import springstudy.spring.exception.CommonResponse;
import springstudy.spring.exception.ErrorResponse;
import springstudy.spring.repository.UserRepository;
import springstudy.spring.security.JwtTokenProvider;
import springstudy.spring.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;


@Api(tags="1. User")
@RequestMapping(value="users")
@RequiredArgsConstructor
@RestController
public class UserController {
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final UserService userService;

    // 회원가입
    //@Valid를 작성하면, @RequestBody로 들어오는 객체(dto)에 대한 검증을 수행
    @ApiOperation(value="회원가입", notes="회원을 입력합니다")  //api 설명
    @PostMapping("/join")
    public ResponseEntity<? extends BasicResponse> join(@Valid @RequestBody UserJoinDto userJoinDto) {

        if(userService.duplictionId(userJoinDto.getUserId())){   //중복된 id
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("중복된 id입니다."));
        }
        else {
            userService.signUp(userJoinDto);
            return ResponseEntity.created(URI.create("/users/member")).build();
        }

    }

    // 로그인
    @ApiOperation(value = "로그인", notes="회원 로그인")
    @PostMapping("/login")
    public String login(@ApiParam(name="로그인",value = "회원 로그인", required=true)
                        @RequestBody LoginDto dto) {

        User member = userService.findByUser(dto.getUserId());

        //해당 user의 id가 존재, but pw 틀림
        if(member != null){
            if (!(passwordEncoder.matches(dto.getUserPassword(), member.getUserPassword()))) {
                throw new IllegalArgumentException("잘못된 비밀번호입니다.");
            }
        } else {
            throw new IllegalArgumentException("가입되지 않은 ID 입니다.");
        }
        //정상적으로 토큰생성
        return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
    }







    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN",
                    value = "로그인 성공 후 access_token",
                    required = true,
                    dataType = "String",
                    paramType = "header")

    })
    @ApiOperation(value = "회원수정", notes = "pw,name,phone 회원정보 수정")
    @PutMapping(value = "/modify")
    public ResponseEntity<? extends BasicResponse> modifyUser(@RequestBody UserJoinDto dto)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        User saveUser = userService.findByUser(id);

        if(saveUser == null){
            //System.out.println("유저 없음");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("일치하는 회원이 아닙니다."));
        }

        User user = this.userService.modify(id,dto);

        return ResponseEntity.ok().body(new CommonResponse<User>(user));
    }







    //user id로 회원 조회
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 단건 조회", notes = "토큰에서 가져온 id로 회원을 조회한다")
    @GetMapping(value = "/member")
    public ResponseEntity<? extends BasicResponse> findUser() {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();   //userId
        User user = userService.findByUser(id);

        return ResponseEntity.ok().body(new CommonResponse<User>(user));

    }


    //회원탈퇴
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 삭제", notes = "회원 id로 회원정보 삭제")
    @DeleteMapping(value = "/delete")
    public ResponseEntity<User> delete(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();   //id는 해당 userId이다.

        User user= userService.findByUser(id);
        userRepository.delete(user);
        SecurityContextHolder.clearContext(); //로그아웃처리
        return ResponseEntity.ok().build();

    }

}
