package org.fdu.awt.minifdustudy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fdu.awt.minifdustudy.bo.club.ClubResp;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Violette
 * @date 2024/5/20 12:10
 */
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "club")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "slogan")
    private String slogan;

    @Column(nullable = false, name = "intro")
    private String intro;

    @Column(nullable = false, name = "profile")
    private String profile;

    @Column(nullable = false, name = "activity")
    private String activity;

    public static ClubResp toClubResp(Club club) {
        return ClubResp.builder()
                .id(club.getId())
                .name(club.getName())
                .slogan(club.getSlogan())
                .intro(club.getIntro())
                .profile(club.getProfile())
                .activity(club.getActivity())
                .build();
    }

    public static List<ClubResp> toClubResp(List<Club> clubList) {
        return clubList.stream().map(Club::toClubResp).collect(Collectors.toList());
    }
}
