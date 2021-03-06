package com.ithar.malik.hibernate.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

@Entity
public class Instructor {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="instructor_detail_id")
    private InstructorDetail instructorDetail;

    @OneToMany(mappedBy = "instructor", cascade =
            {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.DETACH,
                    CascadeType.REFRESH})
    private List<Course> courses;

    // A convenience methods for bi-directional relationship
    public void addCourses(List<Course> newCourses) {

        if (courses == null) {
            courses = new ArrayList<>();
        }

        List<Course> allCourses = new ArrayList<>();
        allCourses.addAll(courses);
        allCourses.addAll(newCourses);

        for(Course course : allCourses) {
            course.setInstructor(this);
        }
    }

}
