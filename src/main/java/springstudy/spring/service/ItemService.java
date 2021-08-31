package springstudy.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springstudy.spring.domain.Item;
import springstudy.spring.dto.ItemDto;
import springstudy.spring.repository.ItemRepository;

import java.util.ArrayList;
import java.util.List;

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
    public ItemDto getItem(Long itemId){
        Item item = itemRepository.findOne(itemId);
        ItemDto itemDto = ItemDto.builder()
                .id(item.getId())
                .name(item.getItemName())
                .itemImage(item.getItemImage())
                .itemCategory(item.getItemCategory())
                .itemQuantity(item.getItemQuantity())
                .itemFrom(item.getItemFrom())
                .itemIntro(item.getItemIntro())
                .itemPrice(item.getItemPrice())
                .itemDescription(item.getItemDescription())
                .itemOptions(item.getItemOptions())
                .build();
        return itemDto;
    }


    // Read All
    @Transactional
    public List<ItemDto> getItemList(){
        List<Item> itemList = itemRepository.findAll();
        List<ItemDto> itemDtoList = new ArrayList<>();

        for (Item item : itemList){
            ItemDto itemDto = ItemDto.builder()
                    .id(item.getId())
                    .itemName(item.getItemName())
                    .itemImage(item.getItemImage())
                    .itemCategory(item.getItemCategory())
                    .itemQuantity(item.getItemQuantity())
                    .itemFrom(item.getItemFrom())
                    .itemIntro(item.getItemIntro())
                    .itemPrice(item.getItemPrice())
                    .itemDescription(item.getItemDescription())
                    .itemOptions(item.getItemOptions())
                    .build();
            itemDtoList.add(itemDto);
        }
        return itemDtoList;
    }

    // Delete
    @Transactional
    public void deleteItem(Long id){
        // deleteById를 Repository에서 구현하는 법?
        itemRepository.deleteById(id);
    }
}
