package org.fdu.awt.minifdustudy.service;

import org.fdu.awt.minifdustudy.bo.record.req.QuizAnswerReq;
import org.fdu.awt.minifdustudy.bo.record.resp.QuizAccuracyResp;
import org.fdu.awt.minifdustudy.bo.record.resp.QuizReviewResp;
import org.fdu.awt.minifdustudy.bo.record.resp.QuizTimeDistributionResp;
import org.fdu.awt.minifdustudy.bo.record.resp.QuizTopicDistributionResp;
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
     * @return 用户所有的作答记录
     */
    List<QuizRecordDTO> getAllQuizRecord(Long userId);

    /**
     * @return 用户在某一时间段内的作答记录（今天、近一周、近一月）
     */
    List<QuizRecordDTO> getQuizRecordByTimeFilter(Long userId, TimeFilter timeFilter);

    /**
     * @return 用户在某一时间段内的答题正确率分析，包括作答总题数、答对题数
     */
    QuizAccuracyResp analyzeQuizAccuracy(Long userId, TimeFilter timeFilter);

    /**
     * @return 用户在某一时间段内的答题类别分布，包括作答类别-该类作答题数-其中做对的题数
     */
    List<QuizTopicDistributionResp> analyzeQuizTopicDistribution(Long userId, TimeFilter timeFilter);

    /**
     * @return 用户在某一时间段内的答题时间分布，包括做题日期-当日做题数量
     */
    List<QuizTimeDistributionResp> analyzeQuizTimeDistribution(Long userId, TimeFilter timeFilter);

    /**
     * @return 用户在某一时间段内的错题复盘，包括做错最多的若干道题、推荐了解的相关知识
     */
    QuizReviewResp analyzeQuizReview(Long userId, TimeFilter timeFilter);
}
