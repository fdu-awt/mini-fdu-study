package org.fdu.awt.minifdustudy.bo.qa.req;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Violette
 * @date 2024/5/14 0:17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizAnswerReq {
    @NotNull(message = "用户id必填")
    private Long userId;

    @NotNull(message = "测试id必填")
    private Long quizId;

    @NotNull(message = "用户答案必填")
    @NotEmpty(message = "用户答案不能为空")
    private String answer;
}
