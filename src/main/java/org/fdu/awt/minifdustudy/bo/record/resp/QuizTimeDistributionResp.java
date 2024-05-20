package org.fdu.awt.minifdustudy.bo.record.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author Violette
 * @date 2024/5/21 1:46
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizTimeDistributionResp {
    String date;  // 日期
    Integer totalCount;  // 当日做题总数
}
