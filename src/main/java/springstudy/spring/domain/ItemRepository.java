package springstudy.spring.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByName(String name);
    Page<Item> findByName(String name, Pageable pageable);

}

// 정렬과 페이지 기능은 스프링 데이터 JPA의 기능 사용하기
