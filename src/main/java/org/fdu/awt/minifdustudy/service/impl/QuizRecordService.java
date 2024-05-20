package org.fdu.awt.minifdustudy.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.fdu.awt.minifdustudy.bo.record.req.QuizAnswerReq;
import org.fdu.awt.minifdustudy.bo.record.resp.QuizAccuracyResp;
import org.fdu.awt.minifdustudy.bo.record.resp.QuizReviewResp;
import org.fdu.awt.minifdustudy.bo.record.resp.QuizTimeDistributionResp;
import org.fdu.awt.minifdustudy.bo.record.resp.QuizTopicDistributionResp;
import org.fdu.awt.minifdustudy.dao.QuizDAO;
import org.fdu.awt.minifdustudy.dao.QuizRecordDAO;
import org.fdu.awt.minifdustudy.dto.QuizRecordDTO;
import org.fdu.awt.minifdustudy.entity.Quiz;
import org.fdu.awt.minifdustudy.entity.QuizRecord;
import org.fdu.awt.minifdustudy.exception.NotExistsException;
import org.fdu.awt.minifdustudy.service.IQuizRecordService;
import org.fdu.awt.minifdustudy.utils.TimeFilter;
import org.fdu.awt.minifdustudy.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Violette
 * @date 2024/5/14 0:44
 */
@Slf4j
@Service
public class QuizRecordService implements IQuizRecordService {
    private final QuizRecordDAO quizRecordDAO;
    private final QuizDAO quizDAO;

    @Autowired
    public QuizRecordService(QuizRecordDAO quizRecordDAO, QuizDAO quizDAO) {
        this.quizRecordDAO = quizRecordDAO;
        this.quizDAO = quizDAO;
    }

    @Override
    public QuizRecordDTO createNewQuizRecord(QuizAnswerReq quizAnswerReq) throws NotExistsException {
        // 检查对应的测试题是否存在
        Quiz quiz = quizDAO.findQuizById(quizAnswerReq.getQuizId());
        if (quiz == null) {
            throw new NotExistsException("Quiz", quizAnswerReq.getQuizId());
        }
        // 检查用户作答是否正确，并保存作答记录
        Boolean isCorrect = quiz.getAnswer().equals(quizAnswerReq.getAnswer());
        QuizRecord quizRecord = QuizRecord.from(quizAnswerReq, quiz, isCorrect);
        quizRecordDAO.save(quizRecord);

        return QuizRecordDTO.from(quizRecord);
    }

    @Override
    public List<QuizRecordDTO> getAllQuizRecord(Long userId) {
        List<QuizRecord> quizRecordList = quizRecordDAO.findAllByUserId(userId);
        return QuizRecordDTO.from(quizRecordList);
    }

    @Override
    public List<QuizRecordDTO> getQuizRecordByTimeFilter(Long userId, TimeFilter timeFilter) {
        Timestamp fromTime = TimeUtils.getFromTimeBasedOnFilter(timeFilter);
        List<QuizRecord> quizRecordList = quizRecordDAO.findQuizRecordFromTime(userId, fromTime);
        return QuizRecordDTO.from(quizRecordList);
    }

    @Override
    public QuizAccuracyResp analyzeQuizAccuracy(Long userId, TimeFilter timeFilter) {
        // 获取用户某一时间段内的作答记录
        Timestamp fromTime = TimeUtils.getFromTimeBasedOnFilter(timeFilter);
        List<QuizRecord> quizRecordList = quizRecordDAO.findQuizRecordFromTime(userId, fromTime);
        // 计算总答题数
        Integer totalCount = quizRecordList.size();
        // 计算答对题数
        Integer correctCount = (int) quizRecordList.stream().filter(QuizRecord::getIsCorrect).count();
        return QuizAccuracyResp.builder()
                .totalCount(totalCount)
                .correctCount(correctCount)
                .build();
    }

    @Override
    public List<QuizTopicDistributionResp> analyzeQuizTopicDistribution(Long userId, TimeFilter timeFilter) {
        // 获取用户某一时间段内的作答记录
        Timestamp fromTime = TimeUtils.getFromTimeBasedOnFilter(timeFilter);
        List<QuizRecord> quizRecordList = quizRecordDAO.findQuizRecordFromTime(userId, fromTime);
        // 按照 topic 分组，统计每个类别的总答题数和答对题数
        Map<String, QuizTopicDistributionResp> topicDistributionMap = new HashMap<>();
        for (QuizRecord quizRecord : quizRecordList) {
            String topic = quizRecord.getQuiz().getTopic();
            QuizTopicDistributionResp topicDistribution = topicDistributionMap
                    .getOrDefault(topic, QuizTopicDistributionResp.builder().topic(topic).build());
            // 更新该类总答题数
            topicDistribution.setTotalCount(topicDistribution.getTotalCount() + 1);
            // 更新该类答对题数
            if (quizRecord.getIsCorrect()) {
                topicDistribution.setCorrectCount(topicDistribution.getCorrectCount() + 1);
            }
            topicDistributionMap.put(topic, topicDistribution);
        }
        return new ArrayList<>(topicDistributionMap.values());
    }

    @Override
    public List<QuizTimeDistributionResp> analyzeQuizTimeDistribution(Long userId, TimeFilter timeFilter) {
        // 获取用户某一时间段内的作答记录
        Timestamp fromTime = TimeUtils.getFromTimeBasedOnFilter(timeFilter);
        List<QuizRecord> quizRecordList = quizRecordDAO.findQuizRecordFromTime(userId, fromTime);
        // 按照答题日期分组，统计当日做题数量
        Map<String, Long> dateDistributionMap = quizRecordList.stream()
                .collect(Collectors.groupingBy(
                        quizRecord -> TimeUtils.extractDate(quizRecord.getCreateTimestamp()),
                        Collectors.counting()
                ));
        // 将 Map 中的统计结果转换为 List<QuizTimeDistributionResp>
        return dateDistributionMap.entrySet().stream()
                .map(entry -> QuizTimeDistributionResp.builder()
                        .date(entry.getKey())
                        .totalCount(entry.getValue().intValue())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public QuizReviewResp analyzeQuizReview(Long userId, TimeFilter timeFilter) {
        return null;
    }

}
