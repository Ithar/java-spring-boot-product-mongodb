package com.ithar.malik.hibernate.main;

import com.ithar.malik.hibernate.domain.Course;
import com.ithar.malik.hibernate.domain.Instructor;
import com.ithar.malik.hibernate.domain.InstructorDetail;
import com.ithar.malik.hibernate.domain.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainOneToMany_CourseToReviewsUnidirectional {

    private static final String COURSE_NAME = "PHP 1.1";

    private static final SessionFactory SESSION_FACTORY = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Instructor.class)
            .addAnnotatedClass(InstructorDetail.class)
            .addAnnotatedClass(Review.class)
            .addAnnotatedClass(Course.class)
            .buildSessionFactory();

    public static void main(String[] args) {
        try {
             //saveCourseWithReviews(SESSION_FACTORY.getCurrentSession());
             getCourseReviews(SESSION_FACTORY.getCurrentSession());
             // deleteReview(SESSION_FACTORY.getCurrentSession());
             // deleteCourse(SESSION_FACTORY.getCurrentSession());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SESSION_FACTORY.close();
        }
    }

    private static void saveCourseWithReviews(Session session) {

        Review review1 = new Review();
        review1.setComment("4 star");

        Review review2 = new Review();
        review2.setComment("1 star");

        Course course = new Course();
        course.setTitle(COURSE_NAME);

        // Start hibernate transaction
        session.beginTransaction();

        session.save(course);

        course.addReview(review1);
        course.addReview(review2);

        // Commit hibernate transaction
        session.getTransaction().commit();
    }

    private static void deleteReview(Session session) {

        // Start hibernate transaction
        session.beginTransaction();

        Review review = getReviewByComment(session, "1 star");

        session.delete(review);

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

    private static void getCourseReviews(Session session) {

        // Start hibernate transaction
        session.beginTransaction();

        Course course = getCourseByTitle(session, COURSE_NAME);


        for (Review review : course.getReviews()) {
            System.out.println(">>>>>>" + review.getComment());
        }

        // Commit hibernate transaction
        session.getTransaction().commit();
    }


    private static Review getReviewByComment(Session session, String comment) {
        return session
                .createQuery("from Review r where r.comment='"+comment+"'", Review.class)
                .uniqueResult();
    }

    private static Course getCourseByTitle(Session session, String title) {
        return session
                .createQuery("from Course c where c.title='"+title+"'", Course.class)
                .uniqueResult();
    }
}
