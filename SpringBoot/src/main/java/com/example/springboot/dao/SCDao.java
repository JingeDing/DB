package com.example.springboot.dao;

import com.example.springboot.pojo.SC;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SCDao {
    public List<SC> selectCourseBySno(String sno);

    public List<SC> selectCourseBySnoThatCanSelect(String sno);

    public List<SC> selectCourseByEverNotSelect();

    public List<SC> selectBySno(String sno);

    public void insertData(@Param("sno") String sno, @Param("no") String no, @Param("weekday") String weekday,
            @Param("ctime") String ctime, @Param("semester") String semester);

    public void deleteSC(@Param("sno") String sno, @Param("no") String no);

    public List<SC> selectMark(String sno);

    public List<SC> selectByNo(String no);

    public void updateGrade(@Param("sno") String sno, @Param("no") String no, @Param("grade") Integer grade,
            @Param("gpa") Double gpa);

    public void insertGrade(@Param("sno") String sno, @Param("no") String no, @Param("grade") Integer grade,
            @Param("gpa") Double gpa);
}
