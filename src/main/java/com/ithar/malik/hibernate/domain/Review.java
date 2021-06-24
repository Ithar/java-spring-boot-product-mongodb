package com.ithar.malik.hibernate.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter

@Entity
public class Review {

    @Id
    private int id;

    private int courseId;

    private String comment;

}
