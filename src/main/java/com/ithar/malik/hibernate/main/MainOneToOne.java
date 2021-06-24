package com.ithar.malik.hibernate.main;

import com.ithar.malik.hibernate.domain.Instructor;
import com.ithar.malik.hibernate.domain.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainOneToOne {

    private static final SessionFactory SESSION_FACTORY = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Instructor.class)
            .addAnnotatedClass(InstructorDetail.class)
            .buildSessionFactory();

    public static void main(String[] args) {
        try {
            //save(SESSION_FACTORY.getCurrentSession());
            delete(SESSION_FACTORY.getCurrentSession());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SESSION_FACTORY.close();
        }
    }

    private static void save(Session session) {

        Instructor instructor = new Instructor();
        instructor.setFirstName("Friedrich");
        instructor.setLastName("Nietzsche");
        instructor.setEmail("friedrich.nietzsche@philosophers.com");

        InstructorDetail detail2 = new InstructorDetail();
        detail2.setBiography("A French philosopher, mathematician, and scientist who invented analytical geometry, linking the previously ...");
        detail2.setWebsite("www.rene-descartes.com");

        Instructor instructor2 = new Instructor();
        instructor2.setFirstName("Rene");
        instructor2.setLastName("Descartes");
        instructor2.setEmail("rene.descartes@philosophers.com");
        instructor2.setInstructorDetail(detail2);

        InstructorDetail detail3 = new InstructorDetail();
        detail3.setBiography("Ludwig Josef Johann Wittgenstein was an Austrian-British philosopher who worked primarily in logic ...");
        detail3.setWebsite("www.ludwig-wittgenstein.com");

        Instructor instructor3 = new Instructor();
        instructor3.setFirstName("Ludwig");
        instructor3.setLastName("Wittgenstein");
        instructor3.setEmail("ludwig.wittgenstein@philosophers.com");
        instructor3.setInstructorDetail(detail3);

        // Start hibernate transaction
        session.beginTransaction();

        session.save(instructor);
        session.save(instructor2);
        session.save(instructor3);

        // Commit hibernate transaction
        session.getTransaction().commit();
    }

    private static void delete(Session session) {

        // Start hibernate transaction
        session.beginTransaction();

        Instructor instructor = session
                                .createQuery("from Instructor i where i.firstName='Ludwig'", Instructor.class)
                                .uniqueResult();

        if (instructor != null) {
            session.delete(instructor);
        }

        // Commit hibernate transaction
        session.getTransaction().commit();

    }

}
