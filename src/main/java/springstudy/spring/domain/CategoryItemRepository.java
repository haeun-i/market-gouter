package springstudy.spring.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryItemRepository extends JpaRepository<CategoryItem, Long> {
    List<CategoryItem> findByName(String name);
}