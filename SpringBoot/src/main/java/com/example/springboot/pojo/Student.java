package com.example.springboot.pojo;

import lombok.Data;

@Data
public class Student {

    private String sno;
    private String sname;
    private String sex;
    private String cno;

    private Department department;
    private Class classItem;

}
