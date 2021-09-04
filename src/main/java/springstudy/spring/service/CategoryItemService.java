package springstudy.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springstudy.spring.domain.CategoryItem;
import springstudy.spring.domain.CategoryItemRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryItemService {
    private final CategoryItemRepository categoryItemRepository;

    // 카테고리 등록
    @Transactional
    public Long addCategoryItem(CategoryItem categoryItem) {
        validateDuplicationCategory(categoryItem);
        categoryItemRepository.save(categoryItem);
        return categoryItem.getId(); // 카테고리명 아이디 리턴
    }

    private void validateDuplicationCategory(CategoryItem categoryItem) {
        List<CategoryItem> findCategoryItems = categoryItemRepository.findByName(categoryItem.getCategoryName());
        if (!findCategoryItems.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 카테고리명입니다. ");
        }
    }

    // 카테고리 전체 조회
    public List<CategoryItem> findCategoryItems() {
        return categoryItemRepository.findAll();
    }


    public CategoryItem findOne(Long categoryItemId) {
        return categoryItemRepository.getOne(categoryItemId);
    }
}