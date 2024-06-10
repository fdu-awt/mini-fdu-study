package org.fdu.awt.minifdustudy.bo.record.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Violette
 * @date 2024/5/21 0:46
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizAccuracyResp {
    Integer totalCount;  // 总答题数
    Integer correctCount;  // 答对的题数
    Integer rank;  // 排名
    Integer userNum;  // 用户总数
}
