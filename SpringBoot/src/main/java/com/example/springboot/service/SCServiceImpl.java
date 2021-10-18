package com.example.springboot.service;

import com.example.springboot.dao.SCDao;
import com.example.springboot.pojo.Course;
import com.example.springboot.pojo.SC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SCServiceImpl implements SCService {

    @Autowired
    private SCDao scDao;

    public List<SC> handle(List<SC> list) {
        for (SC item : list) {
            switch (item.getCourse().getWeekday()) {
                case "1":
                    item.getCourse().setWeekday("周一");
                    break;
                case "2":
                    item.getCourse().setWeekday("周二");
                    break;
                case "3":
                    item.getCourse().setWeekday("周三");
                    break;
                case "4":
                    item.getCourse().setWeekday("周四");
                    break;
                case "5":
                    item.getCourse().setWeekday("周五");
                    break;
                case "6":
                    item.getCourse().setWeekday("周六");
                    break;
                case "7":
                    item.getCourse().setWeekday("周日");
                    break;
            }
            switch (item.getCourse().getCtime()) {
                case "1":
                    item.getCourse().setCtime("08:00-09:40");
                    break;
                case "2":
                    item.getCourse().setCtime("10:10-11:50");
                    break;
                case "3":
                    item.getCourse().setCtime("14:00-15:40");
                    break;
                case "4":
                    item.getCourse().setCtime("16:00-17:40");
                    break;
                case "5":
                    item.getCourse().setCtime("19:00-20:40");
                    break;
            }
        }
        return list;
    }

    @Override
    public List<SC> selectCourseBySno(String sno) {
        List<SC> list = scDao.selectCourseBySno(sno);
        return handle(list);
    }

    @Override
    public List<SC> selectBySno(String sno) {
        return scDao.selectBySno(sno);
    }

    @Override
    public List<SC> selectCourseByEverNotSelect() {
        return scDao.selectCourseByEverNotSelect();
    }

    @Override
    public List<SC> selectCourseBySnoThatCanSelect(String sno) {
        List<SC> List = scDao.selectCourseBySnoThatCanSelect(sno);
        return handle(List);
    }

    @Override
    public void insertData(String sno, String no, String weekday, String ctime, String semester) {
        scDao.insertData(sno, no, weekday, ctime, semester);
    }

    @Override
    public void deleteSC(String sno, String no) {
        scDao.deleteSC(sno, no);
    }

    @Override
    public List<SC> selectMark(String sno) {
        return scDao.selectMark(sno);
    }

    @Override
    public List<SC> selectByNo(String no) {
        return scDao.selectByNo(no);
    }

    @Override
    public void updateGrade(String sno, String no, Integer grade) {
        double gpa = mapper(grade);
        scDao.updateGrade(sno, no, grade, gpa);
    }

    @Override
    public void insertGrade(String sno, String no, Integer grade) {
        double gpa = mapper(grade);
        scDao.insertGrade(sno, no, grade, gpa);
    }

    public double mapper(Integer grade) {
        double gpa;
        if (grade >= 90) {
            gpa = 4.0;
        } else if (grade >= 85) {
            gpa = 3.7;
        } else if (grade >= 80) {
            gpa = 3.2;
        } else if (grade >= 75) {
            gpa = 2.7;
        } else if (grade >= 70) {
            gpa = 2.2;
        } else if (grade >= 65) {
            gpa = 1.7;
        } else if (grade >= 60) {
            gpa = 1.0;
        } else {
            gpa = 0;
        }
        return gpa;
    }
}
