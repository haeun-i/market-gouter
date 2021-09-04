package springstudy.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springstudy.spring.domain.*;
import springstudy.spring.repository.ItemRepository;
import springstudy.spring.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemQuestionService {
    private final ItemQuestionRepository itemQuestionRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    // create Question
    @Transactional
    public void addItemQuestion(ItemQuestion itemQuestion, Long UserId, Long ItemId) {
        User findUser = userRepository.getOne(UserId);
        Item findItem = itemRepository.findOne(ItemId);

        itemQuestion.setUser(findUser);
        itemQuestion.setItem(findItem);

        itemQuestionRepository.save(itemQuestion);
    }

    // ReadAll
    @Transactional
    public List<ItemQuestion> getItemQuestionList() {
        return itemQuestionRepository.findAll();
    }

    // ReadOne
    @Transactional
    public ItemQuestion getItemQuestion(Long itemQuestionId){
        return itemQuestionRepository.getOne(itemQuestionId);
    }


    //Delete
    @Transactional
    public void deleteItemQuestion(Long itemQuestionId){
        ItemQuestion findItemQuestion = itemQuestionRepository.getOne(itemQuestionId);
        itemQuestionRepository.delete(findItemQuestion);
    }
}