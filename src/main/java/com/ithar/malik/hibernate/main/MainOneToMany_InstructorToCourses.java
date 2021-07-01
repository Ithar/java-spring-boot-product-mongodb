package com.ithar.malik.hibernate.main;

import com.ithar.malik.hibernate.domain.Course;
import com.ithar.malik.hibernate.domain.Instructor;
import com.ithar.malik.hibernate.domain.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainOneToMany_InstructorToCourses {

    private static final String INSTRUCTOR_NAME = "Jean-Paul";

    private static final SessionFactory SESSION_FACTORY = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Instructor.class)
            .addAnnotatedClass(InstructorDetail.class)
            .addAnnotatedClass(Course.class)
            .buildSessionFactory();

    public static void main(String[] args) {
        try {
            // createInstructor(SESSION_FACTORY.getCurrentSession());
             saveCoursesToInstructor(SESSION_FACTORY.getCurrentSession());
            // deleteCourse(SESSION_FACTORY.getCurrentSession());
            // deleteInstructor(SESSION_FACTORY.getCurrentSession());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SESSION_FACTORY.close();
        }
    }

    private static void createInstructor(Session session) {

        Instructor instructor = new Instructor();
        instructor.setFirstName(INSTRUCTOR_NAME);
        instructor.setLastName("Sartre");
        instructor.setEmail("jeanpaul.sartre@philosophers.com");

        // Start hibernate transaction
        session.beginTransaction();

        session.save(instructor);

        // Commit hibernate transaction
        session.getTransaction().commit();
    }

    // NOTE: Cannot delete an instructor that is linked to a course
    private static void deleteInstructor(Session session) {

        // Start hibernate transaction
        session.beginTransaction();

        Instructor instructor = getInstructorByFirstname(session, INSTRUCTOR_NAME);

        session.delete(instructor);

        // Commit hibernate transaction
        session.getTransaction().commit();
    }

    private static void saveCoursesToInstructor(Session session) {

        Course course1 = new Course();
        course1.setTitle("Java 1.1");

        Course course2 = new Course();
        course2.setTitle("Java Structs");

        Course course3 = new Course();
        course3.setTitle("Java Spring");

        // Start hibernate transaction
        session.beginTransaction();

        session.save(course1);
        session.save(course2);
        session.save(course3);

        Instructor instructor = getInstructorByFirstname(session, INSTRUCTOR_NAME);
        instructor.addCourse(course1);
        instructor.addCourse(course2);
        instructor.addCourse(course3);

        // Commit hibernate transaction
        session.getTransaction().commit();
    }

    private static void deleteCourse(Session session) {

        // Start hibernate transaction
        session.beginTransaction();

        Course course = getCourseByTitle(session, "Java 1.1");

        session.delete(course);

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
