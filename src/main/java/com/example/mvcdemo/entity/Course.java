package com.example.mvcdemo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
public class  Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String title;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE})
    @JoinColumn(name = "instructor_id")
    private instructor instructor;

    @OneToMany( cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private List<Review> review;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> studentList;

    public Course(){}

    public Course(String title) {
        this.title = title;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Review> getReview() {
        return review;
    }

    public void setReview(List<Review> review) {
        this.review = review;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public com.example.mvcdemo.entity.instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(com.example.mvcdemo.entity.instructor instructor) {
        this.instructor = instructor;
    }

    public void addReview(Review temp){
        if (review == null){
            review = new ArrayList<>();
        }
        review.add(temp);
    }


    public void addStudent(Student student){
        if (studentList == null ){
            studentList = new ArrayList<>();
        }
        studentList.add(student);
    }
    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", instructor=" + instructor +
                '}';
    }
}
