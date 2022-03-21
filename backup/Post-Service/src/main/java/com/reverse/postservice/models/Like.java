package com.reverse.postservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_likes")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Like {

    @EmbeddedId
    private LikeId likeId;
}
