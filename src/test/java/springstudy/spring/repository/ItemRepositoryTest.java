package springstudy.spring.repository;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import springstudy.spring.domain.Item;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

    @Test
    @Transactional
    public void testItem() throws Exception{
        //given
        Item item = new Item();
        item.setItemName("item1");

        //when
        Long saveId = itemRepository.save(item);
        Item findItem = itemRepository.find(saveId);

        //then
        Assertions.assertThat(findItem.getId()).isEqualTo(item.getId());
        Assertions.assertThat(findItem.get())

    }
}