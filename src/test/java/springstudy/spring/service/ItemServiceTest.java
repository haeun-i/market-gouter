package springstudy.spring.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import springstudy.spring.domain.CategoryItem;
import springstudy.spring.domain.Item;
import springstudy.spring.domain.ItemOption;
import springstudy.spring.domain.ItemPhoto;
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

    @Test
    public void 아이템등록() throws Exception {
        //given
        String name = "무농약 간편 단호박 2종";
//        List<ItemPhoto> itemPhotoList = itemPhotoService.getItemPhotos(1L);
        Long photoId = 1L;
        int quantity = 10;
        CategoryItem categoryItem = categoryItemService.findOne(1L);
        String from = "국산";
        String intro = "간편 단호박의 단호한 간편함 (400g)";
        int price = 4300;
        String description = "단호박은 식이섬유와 단백질이 풍부하고 포만감이 커 인기가 많죠. 먹기 편하게 손질되어 있어 식사 대용으로도 좋습니다.";
//        List<ItemOption> itemOptionList =itemOptionService.getItemOptions(1L);
        Long optionId = 1L;

        //when
        Long ItemId = itemService.createItem(name, itemPhotoList)
        //then
    }

    @Test
    public void updateItem() {
    }

    @Test
    public void findItems() {
    }

    @Test
    public void deleteItem() {
    }
}