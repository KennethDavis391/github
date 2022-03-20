package com.reverse.userservice.models;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "profile_pictures")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProfilePicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bucket", nullable = false)
    private ImageLocation imageLocation;

    @Column(name = "image_name", nullable = false, length = 50)
    private String imageName;

    @Override
    @Generated // This generated tag keeps these lines of code from being counting towards/against test coverage
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProfilePicture)) return false;
        ProfilePicture that = (ProfilePicture) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getImageLocation(), that.getImageLocation()) && Objects.equals(getImageName(), that.getImageName());
    }

    @Override
    @Generated // This generated tag keeps these lines of code from being counting towards/against test coverage
    public int hashCode() {
        return Objects.hash(getId(), getImageLocation(), getImageName());
    }
}
