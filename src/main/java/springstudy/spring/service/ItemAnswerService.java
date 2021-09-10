package springstudy.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springstudy.spring.domain.*;
import springstudy.spring.repository.UserRepository;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemAnswerService {
    private final ItemAnswerRepository itemAnswerRepository;
    private final UserRepository userRepository;
    private final ItemQuestionRepository itemQuestionRepository;

    @Transactional
    public void addItemAnswer(Long userId, String itemAnswerContent, Long itemQuestionId ) {
        User findUser = userRepository.getOne(userId);
        ItemQuestion findItemQuestion = itemQuestionRepository.getOne(itemQuestionId);

        ItemAnswer itemAnswer= new ItemAnswer();
        itemAnswer.setItemQuestion(findItemQuestion);
        Date now = new Date();
        itemAnswer.setItemAnswerDate(now);
        itemAnswer.setUser(findUser);
        itemAnswer.setItemAnswerContent(itemAnswerContent);

        itemAnswerRepository.save(itemAnswer);
    }

    /* DELETE */
    @Transactional
    public void deleteItemAnswer(ItemAnswer itemAnswer, Long id){
        ItemAnswer findItemAnswer = itemAnswerRepository.getOne(id);
        itemAnswerRepository.delete(findItemAnswer);

    }
}