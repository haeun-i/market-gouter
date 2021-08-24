package springstudy.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springstudy.spring.domain.*;
import springstudy.spring.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemQuestionService {
    private final ItemQuestionRepository itemQuestionRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public void addItemQuestion(ItemQuestion itemQuestion, Long UserId, Long ItemId){
        User findUser = userRepository.findOne(UserId);
        Item findItem = itemRepository.getOne(ItemId);

        itemQuestion.setUser(findUser);
        itemQuestion.setItem(findItem);

        itemQuestionRepository.save(itemQuestion);
    }


}
