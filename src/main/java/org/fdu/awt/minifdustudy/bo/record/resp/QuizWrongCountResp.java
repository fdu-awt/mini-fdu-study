package org.fdu.awt.minifdustudy.bo.record.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fdu.awt.minifdustudy.dto.QuizDTO;

/**
 * @author Violette
 * @date 2024/5/22 16:24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizWrongCountResp {
    QuizDTO quiz;  // 错题
    Long wrongCount; // 做错的次数
}
