package springstudy.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springstudy.spring.domain.CategoryItem;
import springstudy.spring.domain.CategoryItemRepository;
import springstudy.spring.domain.Item;
import springstudy.spring.domain.ItemRepository;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final CategoryItemRepository categoryItemRepository;

    @Transactional
    public void saveItem(Item item, Long categoryItemId){

        // FK 관계의 categoryItem을 가져옴
        CategoryItem findCategoryItem = categoryItemRepository.getOne(categoryItemId);
        item.setCategoryItem(findCategoryItem);

        itemRepository.save(item);
    }

    @Transactional
    public Item updateItem(Long itemId, Item item){ // update 할 item의 PK값, 덮어씌울 Item 객체 내용
        Item findItem = itemRepository.getOne(itemId);
        findItem.setItemName(item.getItemName());
        findItem.setItemImage(item.getItemImage());
        findItem.setCategoryItem(item.getCategoryItem());
        findItem.setItemQuantity(item.getItemQuantity());
        findItem.setItemFrom(item.getItemFrom());
        findItem.setItemIntro(item.getItemIntro());
        findItem.setItemPrice(item.getItemPrice());
        findItem.setItemDescription(item.getItemDescription());

        findItem.setItemOptions(item.getItemOptions());
        // 사용자 정의 메소드 이용
        return findItem;
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.getOne(itemId);
    }


}


