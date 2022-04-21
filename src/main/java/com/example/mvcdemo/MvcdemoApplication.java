package com.example.mvcdemo;

import com.example.mvcdemo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.hibernate.cfg.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

@SpringBootApplication
public class MvcdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MvcdemoApplication.class, args);
        System.out.println("Running");

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(instructor.class)
                .addAnnotatedClass(instructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try{
            System.out.println("Saving....");
            session.beginTransaction();
            Course course = new Course("Advance Java Language");
            session.save(course);

            Student student1 = new Student("John","wick",  "Hohn@gmail.com");
            Student student2 = new Student("Sriram","K",  "Sriram.k@gmail.com");
            course.addStudent(student1);
            course.addStudent(student2);



            session.save(course);
//            session.save(student2);

            session.getTransaction().commit();
            System.out.println("Done");

        }finally{
            factory.close();
        }

    }
}