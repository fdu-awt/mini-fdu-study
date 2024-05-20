package org.fdu.awt.minifdustudy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fdu.awt.minifdustudy.bo.history.HistoryResp;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Violette
 * @date 2024/5/20 11:39
 */
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "history")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "title")
    private String title;

    @Column(nullable = false, name = "intro")
    private String intro;

    @Column(nullable = false, name = "image")
    private String image;

    public static HistoryResp toHistoryResp(History history) {
        return HistoryResp.builder()
                .id(history.getId())
                .title(history.getTitle())
                .intro(history.getIntro())
                .image(history.getImage())
                .build();
    }

    public static List<HistoryResp> toHistoryResp(List<History> historyList) {
        return historyList.stream().map(History::toHistoryResp).collect(Collectors.toList());
    }

}
