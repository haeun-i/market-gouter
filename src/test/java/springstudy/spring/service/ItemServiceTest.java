package springstudy.spring.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import springstudy.spring.domain.Item;
import springstudy.spring.repository.ItemRepository;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired ItemService itemService;
    @Autowired ItemRepository itemRepository;
    @Autowired EntityManager em;

    @Test
    public void 아이템등록() throws Exception {
        //given

        //when

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