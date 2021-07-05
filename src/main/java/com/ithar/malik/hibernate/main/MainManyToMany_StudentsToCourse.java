package com.ithar.malik.hibernate.main;

import com.ithar.malik.hibernate.domain.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class MainManyToMany_StudentsToCourse {

    private static final String COURSE_NAME = "JAVA in Action";

    private static final SessionFactory SESSION_FACTORY = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Student.class)
            .addAnnotatedClass(Course.class)
            .addAnnotatedClass(Instructor.class)
            .addAnnotatedClass(InstructorDetail.class)
            .addAnnotatedClass(Review.class)
            .buildSessionFactory();

    public static void main(String[] args) {
        try {
             // createCourse(SESSION_FACTORY.getCurrentSession());
             // addStudentsToCourses(SESSION_FACTORY.getCurrentSession());
             // getCourseAndStudents(SESSION_FACTORY.getCurrentSession());
             // deleteStudent(SESSION_FACTORY.getCurrentSession());
             deleteCourse(SESSION_FACTORY.getCurrentSession());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SESSION_FACTORY.close();
        }
    }

    private static void createCourse(Session session) {

        Course course = new Course();
        course.setTitle(COURSE_NAME);

        // Start hibernate transaction
        session.beginTransaction();

        session.save(course);

        // Commit hibernate transaction
        session.getTransaction().commit();
    }

    private static void addStudentsToCourses(Session session) {

        Student student1 = new Student();
        student1.setFirstName("Jane");
        student1.setLastName("Doe");
        student1.setEmail("jane.doe@dead.com");

        Student student2 = new Student();
        student2.setFirstName("John");
        student2.setLastName("Doe");
        student2.setEmail("john.doe@dead.com");

        // Start hibernate transaction
        session.beginTransaction();

        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);

        Course course = getCourseByTitle(session, COURSE_NAME);
        course.addStudents(students);

        session.save(student1);
        session.save(student2);

        // NOTICE: how we did not need to save the course.

        // Commit hibernate transaction
        session.getTransaction().commit();
    }

    private static void getCourseAndStudents(Session session) {

        // Start hibernate transaction
        session.beginTransaction();

        Course course = getCourseByTitle(session, COURSE_NAME);
        List<Student> students = course.getStudents();

        for (Student student :students) {
            System.out.println(student.getFirstName() + " " + student.getLastName());
        }

        // Commit hibernate transaction
        session.getTransaction().commit();
    }

    private static void deleteStudent(Session session) {

        // Start hibernate transaction
        session.beginTransaction();

        Student student = getStudentByFirstName(session, "Jane");

        session.delete(student);

        // Commit hibernate transaction
        session.getTransaction().commit();
    }

    private static void deleteCourse(Session session) {

        // Start hibernate transaction
        session.beginTransaction();

        Course course = getCourseByTitle(session, COURSE_NAME);

        session.delete(course);

        // Commit hibernate transaction
        session.getTransaction().commit();
    }


    private static Course getCourseByTitle(Session session, String title) {
        return session
                .createQuery("from Course c where c.title='"+title+"'", Course.class)
                .uniqueResult();
    }

    private static Student getStudentByFirstName(Session session, String name) {
        return session
                .createQuery("from Student s where s.firstName='"+name+"'", Student.class)
                .uniqueResult();
    }
}
