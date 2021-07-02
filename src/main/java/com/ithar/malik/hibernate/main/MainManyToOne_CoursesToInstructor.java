package com.ithar.malik.hibernate.main;

import com.ithar.malik.hibernate.domain.Course;
import com.ithar.malik.hibernate.domain.Instructor;
import com.ithar.malik.hibernate.domain.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainManyToOne_CoursesToInstructor {

    private static final String INSTRUCTOR1_NAME = "Confucius";
    private static final String INSTRUCTOR2_NAME = "Voltaire";

    private static final SessionFactory SESSION_FACTORY = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Instructor.class)
            .addAnnotatedClass(InstructorDetail.class)
            .addAnnotatedClass(Course.class)
            .buildSessionFactory();

    public static void main(String[] args) {
        try {
             //createInstructor(SESSION_FACTORY.getCurrentSession());
             //saveCoursesToInstructor(SESSION_FACTORY.getCurrentSession());
            deleteCourse(SESSION_FACTORY.getCurrentSession());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SESSION_FACTORY.close();
        }
    }

    private static void createInstructor(Session session) {

        Instructor instructor1 = new Instructor();
        instructor1.setFirstName(INSTRUCTOR1_NAME);
        instructor1.setLastName("");
        instructor1.setEmail("confucius@philosophers.com");

        Instructor instructor2 = new Instructor();
        instructor2.setFirstName(INSTRUCTOR2_NAME);
        instructor2.setLastName("");
        instructor2.setEmail("voltaire@philosophers.com");

        // Start hibernate transaction
        session.beginTransaction();

        session.save(instructor1);
        session.save(instructor2);

        // Commit hibernate transaction
        session.getTransaction().commit();
    }

    private static void deleteCourse(Session session) {

        // Start hibernate transaction
        session.beginTransaction();

        Course course = getCourseByTitle(session, "C# in Action");

        session.delete(course);

        // Commit hibernate transaction
        session.getTransaction().commit();
    }

    private static void saveCoursesToInstructor(Session session) {

        Course course1 = new Course();
        course1.setTitle("C# 1.1");

        Course course2 = new Course();
        course2.setTitle("C# in Action");

        // Start hibernate transaction
        session.beginTransaction();

        Instructor instructor1 = getInstructorByFirstname(session, INSTRUCTOR1_NAME);
        Instructor instructor2 = getInstructorByFirstname(session, INSTRUCTOR2_NAME);

        course1.setInstructor(instructor1);
        course2.setInstructor(instructor2);

        session.save(course1);
        session.save(course2);

        // Commit hibernate transaction
        session.getTransaction().commit();
    }

    private static Instructor getInstructorByFirstname(Session session, String firstName) {
        return session
                .createQuery("from Instructor i where i.firstName='"+firstName+"'", Instructor.class)
                .uniqueResult();
    }

    private static Course getCourseByTitle(Session session, String title) {
        return session
                .createQuery("from Course c where c.title='"+title+"'", Course.class)
                .uniqueResult();
    }
}
