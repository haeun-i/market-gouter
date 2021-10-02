package springstudy.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springstudy.spring.domain.*;

import java.util.List;

import springstudy.spring.repository.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemPhotoRepository itemPhotoRepository;
    private final CategoryItemRepository categoryItemRepository;
    private final ItemOptionRepository itemOptionRepository;
    // Create
    @Transactional
    public Long createItem(String name, Long photoId, int quantity, Long categoryId, String from, String intro, int price,
                         String description, Long optionId){
        List<ItemPhoto> itemPhotos = itemPhotoRepository.findAll(photoId);
        CategoryItem categoryItem = categoryItemRepository.findOne(categoryId);
        List<ItemOption> itemOptions = itemOptionRepository.findAll(optionId);

        Item item = new Item();
        item.createItem(name, itemPhotos, quantity, categoryItem, from, intro, price, description, itemOptions);
        itemRepository.save(item);

        return item.getId();
    } // 매개변수에 Item을 넣는 것과 ItemDto를 넣는 것이 차이?

    // Update
    @Transactional
    public void updateItem(Long itemId, Item item){
        Item findItem = itemRepository.findOne(itemId);
        findItem.setItemName(item.getItemName());
        findItem.setItemPrice(item.getItemPrice());
        findItem.setCategoryItem(item.getCategoryItem());
        findItem.setItemDescription(item.getItemDescription());
        findItem.setItemIntro(item.getItemIntro());
        findItem.setItemFrom(item.getItemFrom());
        findItem.setItemQuantity(item.getItemQuantity());

    }

    // Read One
    public Item getItem(Long itemId){  // builder 말고 set으로 수정
        return itemRepository.findOne(itemId);
    }

    // Read All
    public List<Item> findItems(){
        return itemRepository.findAll();
    }


    // Delete
    @Transactional
    public void deleteItem(Long id){
        Item findItem = itemRepository.findOne(id);
        itemRepository.delete(findItem);
        findItem.cancel();
    }
}