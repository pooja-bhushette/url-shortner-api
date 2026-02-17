package com.example.urlshortnerapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Table(name = "url_mapping", indexes = {
        @Index(name = "idx_shortcode", columnList = "shortCode", unique = true)
})
@Getter
@Setter
public class UrlMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String longUrl;

    @Column(nullable = false, unique = true, length = 10)
    private String shortCode;

    private OffsetDateTime createdAt = OffsetDateTime.now(); // ideally it should be of specific timezone

}
