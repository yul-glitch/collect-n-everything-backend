package com.diy.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customisation")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomisationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customisation_id", nullable = false, unique = true)
    Long customisationId;

    @Column(name = "color_background")
    String colorBackground;

    @Column(name = "color_button")
    String colorButton;

    @Column(name = "font")
    String font;

    @Column(name = "catch_phrase")
    String catchPhrase;

    @Column(name = "store_id")
    Long storeId;

    @Column(name = "created_at", nullable = false)
    LocalDateTime createdAt;
    @Column(name = "updated_at")
    LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    LocalDateTime deletedAt;
}
