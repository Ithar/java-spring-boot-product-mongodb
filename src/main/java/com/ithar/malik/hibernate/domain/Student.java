package com.ithar.malik.hibernate.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
public class Student {

    @Id
    private int id;

    private String firstName;

    private String lastName;

    private String email;

}
