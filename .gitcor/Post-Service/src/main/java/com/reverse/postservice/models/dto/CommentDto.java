package com.reverse.postservice.models.dto;

import com.reverse.postservice.models.User;
import lombok.*;
import net.bytebuddy.agent.builder.AgentBuilder;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "post_comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "post_id")
    private int postId;

    @ManyToOne
    @JoinColumn(name = "commenter_id")
    private User commenter;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "created", nullable = false)
    private Instant created;
}
