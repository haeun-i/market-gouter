package springstudy.spring.domain;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.junit.Assert.*;

public class ItemRepositoryTest {
    //when
    PageRequest pageRequest = PageRequest.of(0,3,Sort.by(Sort.Direction.DESC, "itemName"));
    // 한 페이지당 데이터 3개, 첫 페이지를 노출 + 이름으로 내림차순을 한 값을 가져오기
    //then

}