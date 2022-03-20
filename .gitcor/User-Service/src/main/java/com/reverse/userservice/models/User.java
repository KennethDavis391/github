package com.reverse.userservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

//    @JsonIgnore
    @Column(name = "passwrd", nullable = false, length = 24)
    private String password;

    @Column(name = "date_of_birth", nullable = false)
    private Instant dateOfBirth;

    @ManyToOne(optional = false)
    @JoinColumn(name = "gender", nullable = false)
    private Gender gender;

    @ManyToOne(optional = false)
    @JoinColumn(name = "branch", nullable = false)
    private BranchLocation branch;

    @OneToOne
    @JoinColumn(name = "profile_picture")
    private ProfilePicture profilePicture;

    @Override
    @Generated // This generated tag keeps these lines of code from being counting towards/against test coverage
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId().equals(user.getId()) && getUsername().equals(user.getUsername()) && getEmail().equals(user.getEmail()) && getFirstName().equals(user.getFirstName()) && getLastName().equals(user.getLastName()) && getPassword().equals(user.getPassword()) && getDateOfBirth().equals(user.getDateOfBirth()) && getGender().equals(user.getGender()) && getBranch().equals(user.getBranch()) && getProfilePicture().equals(user.getProfilePicture());
    }

    @Override
    @Generated // This generated tag keeps these lines of code from being counting towards/against test coverage
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getEmail(), getFirstName(), getLastName(), getPassword(), getDateOfBirth(), getGender(), getBranch(), getProfilePicture());
    }
}
