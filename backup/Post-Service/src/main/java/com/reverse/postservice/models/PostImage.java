package com.reverse.postservice.models;

import lombok.*;

import javax.persistence.*;
import java.awt.*;

@Entity
@Table(name = "post_images")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bucket", nullable = false)
    private ImageLocation bucket;

    @Column(name = "image_name", nullable = false, length = 100)
    private String imageName;

    @Column(name = "image_title", length = 50)
    private String imageTitle;

    @Generated
    public String getUrl(){
        return "https://"+bucket.getBucketName()+".s3.amazonaws.com/"+imageName;
    }

}
