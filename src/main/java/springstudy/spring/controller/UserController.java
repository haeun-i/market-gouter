package springstudy.spring.controller;


import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import springstudy.spring.domain.User;
import springstudy.spring.dto.LoginDto;
import springstudy.spring.dto.UserJoinDto;
import springstudy.spring.exception.BasicResponse;
import springstudy.spring.exception.CommonResponse;
import springstudy.spring.exception.ErrorResponse;
import springstudy.spring.repository.UserRepository;
import springstudy.spring.response.DefaultRes;
import springstudy.spring.response.ResponseMessage;
import springstudy.spring.response.StatusCode;
import springstudy.spring.security.JwtTokenProvider;
import springstudy.spring.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;


@Api(tags="1. User")
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

        String user=userService.signUp(userJoinDto);

        if(userService.duplictionId(userJoinDto.getUserId())){   //중복된 id
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("중복된 id입니다."));
        }
        else {
            User newUser = userService.findByUser(user);
            //return ResponseEntity.created(URI.create("/user")).body(new CommonResponse<User>(newUser));
            return ResponseEntity.ok().body(new CommonResponse<String>("회원가입 성공"));
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






    //로그인 된 userId의 dto로 '회원정보 수정'

    //유효한 jwt 토큰을 설정해야만 user 리소스를 사용할 수 있음 -> 헤더 설정
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원수정", notes = "userId 외의 회원정보 수정")
    @PutMapping(value = "/user/{userId}")
    public ResponseEntity<? extends BasicResponse> modifyUser(
            //@ApiParam(value="기존 비밀번호", required = true)@RequestParam String password,
            @RequestBody UserJoinDto dto)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();

//        User member=userService.findByUser(id);
//        System.out.println("기존 pw:"+password+"새로 정한 pw: "+member.getUserPassword());
//
//        if (!(passwordEncoder.matches(password,member.getUserPassword()))) {  //본인확인 pw로 재확인
//            throw new IllegalArgumentException("비밀번호를 정확하게 입력해주세요.");
//        }
//        userService.modify(id,dto,password);
        User user = userService.modify(id,dto);
        //System.out.println("수정 후의 pw: " + user.getUserPassword()); //해싱한 값

        return ResponseEntity.ok().body(new CommonResponse<User>(user));
    }








    //user id로 회원 조회
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 단건 조회", notes = "토큰에서 가져온 id로 회원을 조회한다")
    @GetMapping(value = "/user")
    public ResponseEntity<? extends BasicResponse> findUser() {   //request의 헤더에 토큰값이 있음
        // SecurityContext에서 인증받은 회원의 정보를 얻어온다.

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();   //userId
        //System.out.println("결과: 회원 단건조회를 위한 id=" + id);



        User user = userService.findByUser(id);
        return ResponseEntity.ok().body(new CommonResponse<User>(user));


    }


    //회원탈퇴
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 삭제", notes = "회원 id로 회원정보 삭제")
    @DeleteMapping(value = "/user/{userId}")
    public ResponseEntity<User> delete(
            @ApiParam(value = "회원id", required = true) @RequestBody String userId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();   //id는 해당 userId이다.


        User member= userService.findByUser(id);
        userRepository.delete(member);
        SecurityContextHolder.clearContext(); //로그아웃처리
        return ResponseEntity.ok().build();

    }

}
