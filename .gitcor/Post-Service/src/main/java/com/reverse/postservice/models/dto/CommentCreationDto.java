package com.reverse.postservice.models.dto;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "post_comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentCreationDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "post_id")
    private int postId;

    @Column(name = "commenter_id")
    private int userId;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "created")
    private Instant created;
}
