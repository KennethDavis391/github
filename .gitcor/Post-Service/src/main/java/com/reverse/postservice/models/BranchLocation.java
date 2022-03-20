package com.reverse.postservice.models;

import lombok.*;

import javax.persistence.*;

@Table(name = "branch_locations")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BranchLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;
}