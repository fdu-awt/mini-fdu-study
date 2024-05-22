package org.fdu.awt.minifdustudy.bo.record.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Violette
 * @date 2024/5/22 22:10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecommendLinkResp {
    String topic; // 链接主题
    String title; // 链接名称
    String link;  // 链接
}
