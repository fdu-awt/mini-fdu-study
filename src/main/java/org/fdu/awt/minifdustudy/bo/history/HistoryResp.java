package org.fdu.awt.minifdustudy.bo.history;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Violette
 * @date 2024/5/20 11:47
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryResp {
    private Long id;

    private String title;

    private String intro;

    private String image;
}
