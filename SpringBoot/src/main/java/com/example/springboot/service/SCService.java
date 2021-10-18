package com.example.springboot.service;

import com.example.springboot.pojo.SC;

import java.util.List;

public interface SCService {
    public List<SC> selectCourseBySno(String sno);

    public List<SC> selectBySno(String sno);

    public List<SC> selectCourseByEverNotSelect();

    public List<SC> selectCourseBySnoThatCanSelect(String sno);

    public void insertData(String sno, String no, String weekday, String ctime, String semester);

    public void deleteSC(String sno, String no);

    public List<SC> selectMark(String sno);

    public List<SC> selectByNo(String no);

    public void updateGrade(String sno, String no, Integer grade);

    public void insertGrade(String sno, String no, Integer grade);
}
