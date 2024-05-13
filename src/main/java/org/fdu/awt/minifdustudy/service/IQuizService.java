package org.fdu.awt.minifdustudy.service;

import org.fdu.awt.minifdustudy.dto.QuizDTO;
import org.fdu.awt.minifdustudy.exception.NotExistsException;

public interface IQuizService {
    /**
     * 随机选择一道知识自测题
     *
     * @throws NotExistsException 当数据库中不存在自测题时，抛出异常
     * @return 返回随机选择的一道自测题
     */
    QuizDTO getRandomQuizQuestion() throws NotExistsException;

    /**
     * TODO: 根据用户id选择一道该用户尚未做过的知识自测题
     */
}
