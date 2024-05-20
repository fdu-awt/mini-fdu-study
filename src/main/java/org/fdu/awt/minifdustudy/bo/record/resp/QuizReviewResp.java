package org.fdu.awt.minifdustudy.bo.record.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fdu.awt.minifdustudy.dto.QuizDTO;

import java.util.List;
import java.util.Map;

/**
 * @author Violette
 * @date 2024/5/21 1:48
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizReviewResp {
    // TODO: Map -- 前端解析困难
    Map<QuizDTO, Long> quizDTOWrongMap;  // 做错最多的若干道题，以及做错的次数
    List<String> relatedLinks;  // 推荐了解的相关知识链接 -- 目前后端先写死
}
