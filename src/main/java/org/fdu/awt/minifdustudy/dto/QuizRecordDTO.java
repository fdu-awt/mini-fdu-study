package org.fdu.awt.minifdustudy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fdu.awt.minifdustudy.bo.record.req.QuizAnswerReq;
import org.fdu.awt.minifdustudy.bo.record.resp.QuizAnswerResp;
import org.fdu.awt.minifdustudy.entity.Quiz;
import org.fdu.awt.minifdustudy.entity.QuizRecord;
import org.fdu.awt.minifdustudy.utils.TimeUtils;

import java.sql.Timestamp;

/**
 * @author Violette
 * @date 2024/5/14 0:40
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizRecordDTO {
    private Long id;
    private Long userId;
    private Quiz quiz;
    private String answer;
    private Boolean isCorrect;
    private Timestamp createTimestamp = TimeUtils.now();

    public static QuizRecordDTO from(QuizRecord quizRecord) {
        return QuizRecordDTO.builder()
                .id(quizRecord.getId())
                .userId(quizRecord.getUserId())
                .quiz(quizRecord.getQuiz())
                .answer(quizRecord.getAnswer())
                .isCorrect(quizRecord.getIsCorrect())
                .createTimestamp(quizRecord.getCreateTimestamp())
                .build();
    }

    public static QuizAnswerResp toQuizAnswerResp(QuizRecordDTO quizRecordDTO) {
        return QuizAnswerResp.builder()
                .isCorrect(quizRecordDTO.getIsCorrect())
                .rightAnswer(quizRecordDTO.getQuiz().getAnswer())
                .build();
    }

}
