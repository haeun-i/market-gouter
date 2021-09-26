package springstudy.spring.service;

import com.sun.istack.Nullable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import springstudy.spring.domain.*;
import springstudy.spring.repository.ItemRepository;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired ItemService itemService;
    @Autowired ItemPhotoService itemPhotoService;
    @Autowired CategoryItemService categoryItemService;
    @Autowired ItemOptionService itemOptionService;
    @Autowired EntityManager em;
    @Autowired ItemRepository itemRepository;

    @Test
    public void 아이템등록() throws Exception {
        //given
        String name = "무농약 간편 단호박 2종";
//        List<ItemPhoto> itemPhotoList = itemPhotoService.getItemPhotos(1L);
        Long photoId = 1L;
        int quantity = 10;
        Long categoryId = 1L;
//        CategoryItem categoryItem = categoryItemService.findOne(1L);
        String from = "국산";
        String intro = "간편 단호박의 단호한 간편함 (400g)";
        int price = 4300;
        String description = "단호박은 식이섬유와 단백질이 풍부하고 포만감이 커 인기가 많죠. 먹기 편하게 손질되어 있어 식사 대용으로도 좋습니다.";
//        List<ItemOption> itemOptionList =itemOptionService.getItemOptions(1L);
        Long optionId = 1L;

        //when
        Long itemId = itemService.createItem(name, photoId, quantity, categoryId, from, intro, price, description, optionId );

        //then
        Item item = itemRepository.findOne(itemId);
        assertEquals( ItemStatus.ITEM,  item.getStatus());
        System.out.println("Item의 카테고리는" + item.getItemCategory());
    }

    @Test
    public void 아이템수정() {
        //Given
        Long itemId = 1L;
        Item findItem = itemRepository.findOne(itemId);

        String name = "수정된 아이템 이름";
        String description = "수정된 아이템 설명";
        int price = 6000;

        //When
        findItem.setItemName(name);
        findItem.setItemDescription(description);
        findItem.setItemPrice(price);

        // Then
        itemService.updateItem(itemId, findItem);
    }

    @Test
    public void 아이템조회() {
        Long itemId = 2L;
        Item item = itemService.getItem(itemId);
        System.out.println("찾은 Item의 이름은" + item.getItemName());
        System.out.println("찾은 Item의 가격은" + item.getItemPrice());
    }

    @Test
    public void deleteItem() {
        Long itemId = 2L;
        itemService.deleteItem(itemId);
    }
}