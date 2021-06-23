package com.ithar.malik.hibernate.main;

import java.sql.DriverManager;

public class MySqlConnectionTest {

    public static void main(String[] args) {

        String jdbcUrl = "jdbc:mysql://localhost:3306/university_courses?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
        String user = "root";
        String pass = "my-secret-pw";

        try {
            System.out.println("Connecting to database: " + jdbcUrl);

            DriverManager.getConnection(jdbcUrl, user, pass);

            System.out.println("Connection successful!!!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
