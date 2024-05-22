package org.fdu.awt.minifdustudy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fdu.awt.minifdustudy.bo.record.resp.RecommendLinkResp;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Violette
 * @date 2024/5/22 22:12
 */
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recommend_link")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RecommendLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "topic")
    private String topic;

    @Column(nullable = false, name = "title")
    private String title;

    @Column(nullable = false, name = "link")
    private String link;

    public static RecommendLinkResp toRecommendLinkResp(RecommendLink recommendLink) {
        return RecommendLinkResp.builder()
                .topic(recommendLink.getTopic())
                .title(recommendLink.getTitle())
                .link(recommendLink.getLink())
                .build();
    }

    public static List<RecommendLinkResp> toRecommendLinkResp(List<RecommendLink> recommendLinkList) {
        return recommendLinkList.stream().map(RecommendLink::toRecommendLinkResp).collect(Collectors.toList());
    }
}
