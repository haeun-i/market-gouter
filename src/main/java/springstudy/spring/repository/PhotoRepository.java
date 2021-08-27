package springstudy.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springstudy.spring.domain.RecipePhoto;

public interface PhotoRepository extends JpaRepository<RecipePhoto, Long> {
}
