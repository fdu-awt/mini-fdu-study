package org.fdu.awt.minifdustudy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    @JoinColumn(name = "quiz_id", nullable = false, insertable = false, updatable = false)
    private Quiz quiz;

//    @Column(nullable = false, name = "quiz_id")
//    private Long quizId;

    @Column(nullable = false, name = "answer")
    private String answer;  // 用户回答

    @Column(nullable = false, name = "is_correct")
    private Boolean isCorrect;

    @Column(nullable = false, name = "create_time")
    @Builder.Default
    private Timestamp createTimestamp = TimeUtils.now();
}
