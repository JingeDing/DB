package com.example.springboot.controller;

import com.example.springboot.Response.Response;
import com.example.springboot.pojo.SC;
import com.example.springboot.service.CourseService;
import com.example.springboot.service.SCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("sc")
public class SCController {

    @Autowired
    private SCService service;

    @RequestMapping("selectBySno/{sno}")
    public List<SC> selectCourseBySno(@PathVariable("sno") String sno) {
        return service.selectCourseBySno(sno);
    }

    @RequestMapping("selectCourseBySnoThatCanSelect/{sno}")
    public List<SC> selectCourseBySnoThatCanSelect(@PathVariable("sno") String sno) {
        List<SC> list = service.selectBySno(sno);
        if (null == list || list.size() == 0) {
            return service.selectCourseByEverNotSelect();
        } else {
            return service.selectCourseBySnoThatCanSelect(sno);
        }
    }

    @RequestMapping("queryBySno/{sno}")
    public List<SC> selectBySno(@PathVariable String sno) {
        return service.selectBySno(sno);
    }

    @RequestMapping("selectCourseByEverNotSelect")
    public List<SC> selectByEverNOt() {
        return service.selectCourseByEverNotSelect();
    }

    @RequestMapping("insert/{sno}/{no}/{weekday}/{ctime}/{semester}")
    public Response insertData(@PathVariable("sno") String sno, @PathVariable("no") String no,
            @PathVariable("weekday") String weekday, @PathVariable("ctime") String ctime,
            @PathVariable("semester") String semester) {
        try {
            service.insertData(sno, no, weekday, ctime, semester);
            return new Response(200, "success", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(500, "error", null);
        }
    }

    @RequestMapping("delete/{sno}/{no}")
    public Response deleteSC(@PathVariable("sno") String sno, @PathVariable("no") String no) {
        try {
            service.deleteSC(sno, no);
            return new Response(200, "success", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(500, "error", null);
        }
    }

    @RequestMapping("selectMark/{sno}")
    public List<SC> selectMark(@PathVariable("sno") String sno) {
        return service.selectMark(sno);
    }

    @RequestMapping("selectByNo/{no}")
    public List<SC> selectByNo(@PathVariable("no") String no) {
        return service.selectByNo(no);
    }

    @RequestMapping("updateGrade/{sno}/{no}/{grade}")
    public Response updateGrade(@PathVariable("sno") String sno, @PathVariable("no") String no,
            @PathVariable("grade") String grade) {
        try {
            int mark = Integer.parseInt(grade);
            service.updateGrade(sno, no, mark);
            return new Response(200, "success", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(500, "error", null);
        }
    }

    @RequestMapping("insertSC/{sno}/{no}/{grade}")
    public Response insertGrade(@PathVariable("sno") String sno, @PathVariable("no") String no,
            @PathVariable("grade") String grade) {
        try {
            int mark = Integer.parseInt(grade);
            service.insertGrade(sno, no, mark);
            return new Response(200, "success", null);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new Response(500, "error", null);
        }
    }

}
