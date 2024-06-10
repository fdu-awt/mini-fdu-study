package org.fdu.awt.minifdustudy.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.fdu.awt.minifdustudy.bo.record.req.QuizAnswerReq;
import org.fdu.awt.minifdustudy.bo.record.resp.*;
import org.fdu.awt.minifdustudy.dao.QuizDAO;
import org.fdu.awt.minifdustudy.dao.QuizRecordDAO;
import org.fdu.awt.minifdustudy.dao.RecommendLinkDAO;
import org.fdu.awt.minifdustudy.dto.QuizAccuracyDTO;
import org.fdu.awt.minifdustudy.dto.QuizDTO;
import org.fdu.awt.minifdustudy.dto.QuizRecordDTO;
import org.fdu.awt.minifdustudy.entity.Quiz;
import org.fdu.awt.minifdustudy.entity.QuizRecord;
import org.fdu.awt.minifdustudy.entity.RecommendLink;
import org.fdu.awt.minifdustudy.exception.NotExistsException;
import org.fdu.awt.minifdustudy.service.IQuizRecordService;
import org.fdu.awt.minifdustudy.utils.TimeFilter;
import org.fdu.awt.minifdustudy.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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
    private final RecommendLinkDAO recommendLinkDAO;

    @Autowired
    public QuizRecordService(QuizRecordDAO quizRecordDAO, QuizDAO quizDAO, RecommendLinkDAO recommendLinkDAO) {
        this.quizRecordDAO = quizRecordDAO;
        this.quizDAO = quizDAO;
        this.recommendLinkDAO = recommendLinkDAO;
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
        // 计算用户正确率排名（在所有用户中，时间为所有）
        List<QuizAccuracyDTO> usersAcc = findAllUsersAccuracy();
        Integer rank = findUserPosition(usersAcc, userId);
        return QuizAccuracyResp.builder()
                .totalCount(totalCount)
                .correctCount(correctCount)
                .rank(rank)
                .userNum(usersAcc.size())
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
        // 获取用户某一时间段内的作答记录
        Timestamp fromTime = TimeUtils.getFromTimeBasedOnFilter(timeFilter);
        List<QuizRecord> quizRecordList = quizRecordDAO.findQuizRecordFromTime(userId, fromTime);
        // 过滤留下作答错误的题目，并统计答错的次数
        Map<QuizDTO, Long> wrongQuestionsCount = quizRecordList.stream()
                .filter(record -> !record.getIsCorrect())  // 只保留错误作答的记录
                .collect(Collectors.groupingBy(
                        record -> QuizDTO.from(record.getQuiz()),  // 根据题目进行分组
                        Collectors.counting()  // 计算该题答错次数
                ));
        // 将Map<QuizDTO, Long>转换为List<Map.Entry<QuizDTO, Long>>并按错误次数降序排序
        List<Map.Entry<QuizDTO, Long>> sortedEntries = wrongQuestionsCount.entrySet().stream()
                .sorted(Map.Entry.<QuizDTO, Long>comparingByValue().reversed())  // 按错误次数降序排序
                .toList();
        // 将Map<QuizDTO, Long>转换为List<QuizWrongCountResp>
        List<QuizWrongCountResp> wrongCountList = sortedEntries.stream()
                .map(entry -> QuizWrongCountResp.builder()
                        .quiz(entry.getKey())
                        .wrongCount(entry.getValue())
                        .build())
                .toList();

        // 获取随机三个推荐链接
        List<RecommendLink> relatedLinks = recommendLinkDAO.getRandomRecommendLinks();

        return QuizReviewResp.builder()
                .wrongCountList(wrongCountList)
                .relatedLinks(RecommendLink.toRecommendLinkResp(relatedLinks))
                .build();
    }

    // 获取所有用户的做题记录，并按照正确率降序排序
    private List<QuizAccuracyDTO> findAllUsersAccuracy() {
        // 获取所有的答题记录
        List<QuizRecord> quizRecords = quizRecordDAO.findAll();
        // 计算每个用户的答对题数和答题总数
        Map<Long, QuizAccuracyDTO> userAccuracyMap = new HashMap<>();
        for (QuizRecord quizRecord : quizRecords) {
            Long userId = quizRecord.getUserId();
            boolean isCorrect = quizRecord.getIsCorrect();
            QuizAccuracyDTO userAccuracy = userAccuracyMap.getOrDefault(userId,
                    QuizAccuracyDTO.builder().userId(userId).build());
            userAccuracy.addTotalCount(); // 答题总数加1
            if (isCorrect) {
                userAccuracy.addCorrectCount(); // 答对题数加1
            }
            userAccuracyMap.put(userId, userAccuracy);
        }
        // 计算每个用户的正确率
        return userAccuracyMap.values().stream()
                .peek(QuizAccuracyDTO::calculateAccuracy) // 计算正确率
                .collect(Collectors.toList());
    }

    // 查找某用户排序次序的函数
    private static Integer findUserPosition(List<QuizAccuracyDTO> usersAccuracy, Long userId) {
        // 根据正确率降序排序
        usersAccuracy.sort((a, b) -> b.getAccuracy().compareTo(a.getAccuracy()));
        // 计算用户排名，正确率相同时排名相同
        int rank = 0;
        int pos = 0;
        Float lastAcc = -1.0F;
        for (QuizAccuracyDTO user: usersAccuracy) {
            // 当前用户准确率和前一个用户相同，则不增加排名，只增加位置
            if (user.getAccuracy().equals(lastAcc)) {
                pos++;
            } else {
                pos++;
                rank = pos;
                lastAcc = user.getAccuracy();
            }
            // 找到对应的用户，返回排名
            if (user.getUserId().equals(userId)) {
                return rank;
            }
        }
        return null;
    }

}
