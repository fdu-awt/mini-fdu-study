package org.fdu.awt.minifdustudy.dto;

import org.fdu.awt.minifdustudy.bo.quiz.resp.QuizQuestionResp;
import org.fdu.awt.minifdustudy.entity.Quiz;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Violette
 * @date 2024/5/13 18:37
 * @see Quiz
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizDTO {
    private Long id;
    private String topic;
    private Integer dataId;
    private String question;
    private String options;
    private String answer;

    public static QuizDTO from(Quiz quiz) {
        return QuizDTO.builder()
                .id(quiz.getId())
                .topic(quiz.getTopic())
                .dataId(quiz.getDataId())
                .question(quiz.getQuestion())
                .options(quiz.getOptions())
                .answer(quiz.getAnswer())
                .build();
    }

    public static QuizQuestionResp toQuestionResp(QuizDTO quizDTO) {
        return QuizQuestionResp.builder()
                .id(quizDTO.getId())
                .topic(quizDTO.getTopic())
                .dataId(quizDTO.getDataId())
                .question(quizDTO.getQuestion())
                .options(quizDTO.getOptions())
                .build();
    }
}
