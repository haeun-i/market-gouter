package springstudy.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springstudy.spring.domain.Item;
import springstudy.spring.domain.ItemOption;
import springstudy.spring.domain.ItemPhoto;
import springstudy.spring.repository.ItemOptionRepository;
import springstudy.spring.repository.ItemRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemOptionService {
    private final ItemRepository itemRepository;
    private final ItemOptionRepository itemOptionRepository;

    // create
    public Long saveItemOption(String name, Long stock, Long itemId){
        Item item = itemRepository.findOne(itemId);
        ItemOption itemOption = new ItemOption();
        itemOption.createItemOption(item, name, stock);
        itemOptionRepository.save(itemOption);

        return itemOption.getId();
    }

    // Read One
    public ItemOption getItemOption(Long itemOptionId){
        return itemOptionRepository.findOne(itemOptionId);
    }

    // Read All
    public List<ItemOption> getItemOptions(Long itemId){
        return itemOptionRepository.findAll(itemId);
    }
}
