package com.reverse.postservice.models;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "posts")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "poster_id")
    private User poster;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "body", nullable = false)
    private String body;

    @Column(name = "created", nullable = false)
    private Instant created;

    @Column(name = "last_edited")
    private Instant lastEdited;
}
