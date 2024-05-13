package org.fdu.awt.minifdustudy.service;

import org.fdu.awt.minifdustudy.bo.record.req.QuizAnswerReq;
import org.fdu.awt.minifdustudy.dto.QuizRecordDTO;
import org.fdu.awt.minifdustudy.exception.NotExistsException;
import org.springframework.transaction.annotation.Transactional;

public interface IQuizRecordService {
    /**
     * @param quizAnswerReq 用户对某测试题目的回答
     * @throws NotExistsException 如果测试题目不存在，抛出异常
     * @return 用户作答记录的 DTO数据
     */
    @Transactional
    QuizRecordDTO createNewQuizRecord(QuizAnswerReq quizAnswerReq) throws NotExistsException;
}
