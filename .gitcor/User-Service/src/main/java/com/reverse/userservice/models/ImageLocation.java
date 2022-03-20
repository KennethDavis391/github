package com.reverse.userservice.models;

import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "image_locations")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class ImageLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "bucket_name", nullable = false, length = 200)
    private String bucketName;

    @Override
    @Generated // This generated tag keeps these lines of code from being counting towards/against test coverage
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImageLocation)) return false;
        ImageLocation that = (ImageLocation) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getBucketName(), that.getBucketName());
    }

    @Override
    @Generated // This generated tag keeps these lines of code from being counting towards/against test coverage
    public int hashCode() {
        return Objects.hash(getId(), getBucketName());
    }
}
