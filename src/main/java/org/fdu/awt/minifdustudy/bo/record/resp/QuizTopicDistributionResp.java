package org.fdu.awt.minifdustudy.bo.record.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Violette
 * @date 2024/5/21 1:31
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizTopicDistributionResp {
    String topic; // 答题类别

    @Builder.Default
    Integer totalCount = 0;  // 该类总答题数

    @Builder.Default
    Integer correctCount = 0;  // 该类答对的题数
}
