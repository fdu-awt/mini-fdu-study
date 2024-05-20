package org.fdu.awt.minifdustudy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fdu.awt.minifdustudy.bo.record.req.QuizAnswerReq;
import org.fdu.awt.minifdustudy.utils.TimeUtils;

import java.sql.Timestamp;

/**
 * @author Violette
 * @date 2024/5/13 23:28
 */
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "quiz_record")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class QuizRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(
            name = "quiz_id", // 指定外键列的名称为 quiz_id
            nullable = false, // 指定此列不能为 null，意味着每个实体必须关联一个 Quiz
            insertable = true, // 允许在插入时通过 ORM 框架插入这个字段的值
            updatable = false  // 一旦创建记录后，不允许通过 ORM 框架更新这个字段的值
    )
    private Quiz quiz;

    @Column(nullable = false, name = "answer")
    private String answer;  // 用户回答

    @Column(nullable = false, name = "is_correct")
    @Builder.Default
    private Boolean isCorrect = false;

    @Column(nullable = false, name = "create_timestamp")
    @Builder.Default
    private Timestamp createTimestamp = TimeUtils.now();

    public static QuizRecord from(QuizAnswerReq quizAnswerReq, Quiz quiz, Boolean isCorrect) {
        return QuizRecord.builder()
                .userId(quizAnswerReq.getUserId())
                .quiz(quiz)
                .answer(quizAnswerReq.getAnswer())
                .isCorrect(isCorrect)
                .build();
    }
}
