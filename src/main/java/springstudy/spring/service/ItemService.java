package springstudy.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springstudy.spring.domain.Item;
import java.util.List;
import springstudy.spring.dto.ItemDto;
import springstudy.spring.repository.ItemRepository;
import java.util.ArrayList;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    // Create
    @Transactional
    public void saveItem(Item item){

        itemRepository.save(item);
    } // 매개변수에 Item을 넣는 것과 ItemDto를 넣는 것이 차이?

    // Update
    @Transactional
    public void updateItem(Long itemId, Item item){
        Item findItem = itemRepository.findOne(itemId);

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
    }
}