package org.fdu.awt.minifdustudy.bo.record.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Violette
 * @date 2024/5/14 0:35
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizAnswerResp {
    private Boolean isCorrect;
    private String rightAnswer;
}
