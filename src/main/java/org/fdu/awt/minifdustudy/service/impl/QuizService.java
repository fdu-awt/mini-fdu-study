package org.fdu.awt.minifdustudy.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.fdu.awt.minifdustudy.dao.QuizDAO;
import org.fdu.awt.minifdustudy.dto.QuizDTO;
import org.fdu.awt.minifdustudy.entity.Quiz;
import org.fdu.awt.minifdustudy.exception.NotExistsException;
import org.fdu.awt.minifdustudy.service.IQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Violette
 * @date 2024/5/13 18:51
 */
@Slf4j
@Service
public class QuizService implements IQuizService {
    private final QuizDAO quizDAO;

    @Autowired
    public QuizService(QuizDAO quizDAO) {
        this.quizDAO = quizDAO;
    }

    @Override
    public QuizDTO getRandomQuizQuestion() throws NotExistsException {
        Quiz quiz = quizDAO.getRandomQuiz();
        if (quiz == null) {
            throw new NotExistsException("暂时没有习题用于自测~");
        }
        return QuizDTO.from(quiz);
    }
}
