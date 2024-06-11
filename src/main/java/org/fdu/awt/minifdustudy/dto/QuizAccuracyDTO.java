package org.fdu.awt.minifdustudy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Violette
 * @date 2024/5/22 23:43
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizAccuracyDTO {
    private Long userId;

    @Builder.Default
    private Long totalCount = 0L;  // 答题总数

    @Builder.Default
    private Long correctCount = 0L;  // 答对题数

    @Builder.Default
    private Float accuracy = 0F;

    public void calculateAccuracy() {
        if (this.totalCount == 0) {
            this.accuracy = 0.0f;
        } else {
            this.accuracy = (float) correctCount / totalCount;
        }
    }

    public void addTotalCount() {
        this.totalCount++;
    }

    public void addCorrectCount() {
        this.correctCount++;
    }
}