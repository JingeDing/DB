package com.example.springboot.pojo;

import lombok.Data;

@Data
public class Teacher {
    private String tno;
    private String tname;
    private String title;
    private String email;
    private String dno;
    private Department department;
    private Course course;
    private SC sc;
}
