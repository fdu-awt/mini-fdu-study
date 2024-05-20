package org.fdu.awt.minifdustudy.bo.club;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Violette
 * @date 2024/5/20 12:13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClubResp {
    private Long id;

    private String name;

    private String slogan;

    private String intro;

    private String profile;

    private String activity;
}
