package com.ithar.malik.hibernate.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
public class InstructorDetail {

    @Id
    //@GeneratedValue(strategy= GenerationType.AUTO) // default
    private int id;

    @Column(name = "bio")
    private String biography;

    private String website;

}
