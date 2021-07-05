package com.ithar.malik.hibernate.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String title;

    @ManyToOne(cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.DETACH,
                    CascadeType.REFRESH})
    @JoinColumn(name="instructor_id")
    private Instructor instructor;

    @ManyToMany(fetch=FetchType.LAZY,  cascade = {CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.REFRESH})
    @JoinTable(name="course_student",
            joinColumns = @JoinColumn(name="course_id"),
            inverseJoinColumns = @JoinColumn(name= "student_id"))
    private List<Student> students;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Review> reviews;

    // Convenience method to add single student
    public void addStudent(Student student) {

        if (students == null) {
            students = new ArrayList<>();
        }

        students.add(student);
    }

    public void addStudents(List<Student> newStudents) {

        if (students == null) {
            students = new ArrayList<>();
        }

        students.addAll(newStudents);
    }

    // Convenience method to add single review
    public void addReview(Review review) {

        if (reviews == null) {
            reviews = new ArrayList<>();
        }

        review.setCourse(this);
        reviews.add(review);

    }

}
