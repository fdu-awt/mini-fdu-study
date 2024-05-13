package org.fdu.awt.minifdustudy.service;

import org.fdu.awt.minifdustudy.dto.QuizDTO;
import org.fdu.awt.minifdustudy.exception.NotExistsException;

public interface IQuizService {
    /**
     * @throws NotExistsException 当数据库中不存在测试题目时，抛出异常
     * @return 随机选择的一道测试题目
     */
    QuizDTO getRandomQuizQuestion() throws NotExistsException;

    /**
     * TODO: 根据用户id选择一道该用户尚未做过的知识自测题
     */
}
