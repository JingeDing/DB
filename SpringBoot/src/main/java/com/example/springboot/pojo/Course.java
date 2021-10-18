package com.example.springboot.pojo;

import lombok.Data;

@Data
public class Course {
    private String no;
    private String name;
    private Integer credit;
    private String semester;
    private String tno;
    private String weekday;
    private String ctime;
    private Teacher teacher;
    private SC sc;

}
