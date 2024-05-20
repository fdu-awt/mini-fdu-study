package org.fdu.awt.minifdustudy.service.impl;

import org.fdu.awt.minifdustudy.bo.record.req.QuizAnswerReq;
import org.fdu.awt.minifdustudy.dao.QuizDAO;
import org.fdu.awt.minifdustudy.dao.QuizRecordDAO;
import org.fdu.awt.minifdustudy.dto.QuizRecordDTO;
import org.fdu.awt.minifdustudy.entity.Quiz;
import org.fdu.awt.minifdustudy.entity.QuizRecord;
import org.fdu.awt.minifdustudy.exception.NotExistsException;
import org.fdu.awt.minifdustudy.service.IQuizRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Violette
 * @date 2024/5/14 0:44
 */
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
        // TODO: 检查对应的用户是否存在

        // 检查对应的测试题是否存在
        Quiz quiz = quizDAO.findQuizById(quizAnswerReq.getQuizId());
        if (quiz == null) {
            throw new NotExistsException("Quiz", quizAnswerReq.getQuizId());
        }
        // 检查用户作答是否正确，并保存作答记录
        Boolean isCorrect = quiz.getAnswer().equals(quizAnswerReq.getAnswer());
        QuizRecord quizRecord = QuizRecord.from(quizAnswerReq, isCorrect);
        quizRecordDAO.save(quizRecord);

        return QuizRecordDTO.from(quizRecord, quiz);
    }
}