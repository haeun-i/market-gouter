package springstudy.spring.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemPhotoRepository extends JpaRepository<ItemPhoto, Long> {
    ItemPhoto save(ItemPhoto itemPhoto);
    List<ItemPhoto> findAllByItemId(Long itemId);
}
