package springstudy.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springstudy.spring.domain.CategoryItem;
import springstudy.spring.repository.CategoryItemRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Component
public class CategoryItemService {
    private final CategoryItemRepository categoryItemRepository;

    // 카테고리 등록
    @Transactional
    public Long addCategoryItem(CategoryItem categoryItem) {
        categoryItemRepository.save(categoryItem);
        return categoryItem.getId(); // 카테고리명 아이디 리턴
    }

    // 카테고리 전체 조회
    public List<CategoryItem> findCategoryItems() {
        return categoryItemRepository.findAll();
    }


    public CategoryItem findOne(Long categoryItemId) {
        return categoryItemRepository.findOne(categoryItemId);
    }
}