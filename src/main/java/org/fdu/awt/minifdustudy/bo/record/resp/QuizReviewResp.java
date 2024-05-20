package org.fdu.awt.minifdustudy.bo.record.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fdu.awt.minifdustudy.dto.QuizDTO;

import java.util.List;

/**
 * @author Violette
 * @date 2024/5/21 1:48
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizReviewResp {
    List<QuizDTO> quizDTOList;  // 做错最多的n道题
    List<String> relatedLinks;  // 推荐了解的相关知识链接 -- 目前后端先写死
}
