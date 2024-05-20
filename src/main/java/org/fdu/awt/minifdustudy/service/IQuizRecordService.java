package org.fdu.awt.minifdustudy.service;

import org.fdu.awt.minifdustudy.bo.record.req.QuizAnswerReq;
import org.fdu.awt.minifdustudy.dto.QuizRecordDTO;
import org.fdu.awt.minifdustudy.exception.NotExistsException;
import org.fdu.awt.minifdustudy.utils.TimeFilter;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IQuizRecordService {
    /**
     * @param quizAnswerReq 用户对某测试题目的回答
     * @return 用户作答记录的 DTO数据
     * @throws NotExistsException 如果测试题目不存在，抛出异常
     */
    @Transactional
    QuizRecordDTO createNewQuizRecord(QuizAnswerReq quizAnswerReq) throws NotExistsException;

    /**
     * @return 用户的所有作答记录
     */
    List<QuizRecordDTO> getAllQuizRecord(Long userId);

    /**
     * @return 用户在某一时间段内的作答记录（今天、近一周、近一月）
     */
    List<QuizRecordDTO> getQuizRecordByTimeFilter(Long userId, TimeFilter timeFilter);
}
