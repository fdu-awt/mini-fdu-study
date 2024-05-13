package org.fdu.awt.minifdustudy.bo.qa.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Violette
 * @date 2024/5/13 18:45
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizQuestionResp {
    private Long id;
    private String topic;
    private Integer dataId;
    private String question;
    private String options;

    // 不返回答案
}
