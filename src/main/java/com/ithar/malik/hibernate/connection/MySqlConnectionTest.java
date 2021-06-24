package com.ithar.malik.hibernate.connection;

import java.sql.DriverManager;

public class MySqlConnectionTest {

    public static void main(String[] args) {

        String jdbcUrl = "jdbc:mysql://localhost:3306/university_courses?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
        String user = "root";
        String pass = "my-secret-pw";

        try {
            DriverManager.getConnection(jdbcUrl, user, pass);
            System.out.println("\n-------------------------");
            System.out.println("CONNECTED SUCCESSFULLY !!!");
            System.out.println("-------------------------\n");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
