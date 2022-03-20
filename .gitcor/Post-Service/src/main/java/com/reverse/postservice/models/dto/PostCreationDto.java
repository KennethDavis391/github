package com.reverse.postservice.models.dto;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCreationDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "poster_id")
    private Integer posterId;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "body", nullable = false)
    private String body;

    @Column(name = "created", nullable = false)
    private Instant created;

    @Column(name = "last_edited")
    private Instant lastEdited;

    @Transient
    private List<ImageDto> images;

}
