package springstudy.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springstudy.spring.domain.Gallery;

import java.util.List;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {
    @Override
    List<Gallery> findAll();
}
// Override는 상속 관계의 클래스에서 새로운 메서드를 만들어 냈음을 알린다.