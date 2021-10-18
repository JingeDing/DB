package com.example.springboot.pojo;

import lombok.Data;

@Data
public class Class {
    private String cno;
    private String cname;
    private Integer year;
    private String major;
    private String dno;
    private Department department;
    private String cyear;
}
