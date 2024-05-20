package org.fdu.awt.minifdustudy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Violette
 * @date 2024/5/13 18:05
 */
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "quiz")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "topic")
    private String topic;

    @Column(nullable = false, name = "data_id")
    private Long dataId;

    @Column(nullable = false, name = "question")
    private String question;

    @Column(nullable = false, name = "options")
    private String options;

    @Column(nullable = false, name = "answer")
    private String answer;
}
