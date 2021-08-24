package springstudy.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springstudy.spring.domain.*;
import springstudy.spring.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemAnswerService {
        private final ItemAnswerRepository itemAnswerRepository;
        private final UserRepository userRepository;
        private final ItemQuestionRepository itemQuestionRepository;

        @Transactional
        public void addItemAnswer(ItemAnswer itemAnswer, Long userId, Long ItemQuestionId)
        {
            User findUser = userRepository.findOne(userId);
            ItemQuestion findItemQuestion = itemQuestionRepository.getOne(ItemQuestionId);

            itemAnswer.setUser(findUser);
            itemAnswer.setItemQuestion(findItemQuestion);

            itemAnswerRepository.save(itemAnswer);
        }
}
