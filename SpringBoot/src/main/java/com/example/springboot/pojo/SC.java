package com.example.springboot.pojo;

import lombok.Data;

@Data
public class SC {
    private String sno;
    private String no;
    private Integer grade;
    private Double gpa;
    private Course course;
    private Teacher teacher;
    private Student student;

}
