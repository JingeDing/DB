package com.example.springboot.controller;

import com.example.springboot.Response.Response;
import com.example.springboot.pojo.Class;
import com.example.springboot.pojo.Course;
import com.example.springboot.service.CourseService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("course")
public class CourseController {

    @Autowired
    private CourseService cs;

    /**
     * 分页查询
     * 
     * @param pageNum  页数
     * @param pageSize 每页大小
     * @return
     */
    @RequestMapping("selectAll/{pageNum}/{pageSize}")
    public PageInfo<Course> listPage(@PathVariable("pageNum") Integer pageNum,
            @PathVariable("pageSize") Integer pageSize) {
        return cs.selectAllCourse(pageNum, pageSize);
    }

    @RequestMapping(value = "addCourse", method = RequestMethod.POST)
    public Response addCourse(@RequestBody Course course) {
        try {
            cs.addCourse(course);
            return new Response(200, "success", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(500, "error", null);
        }
    }

    @RequestMapping("selectById/{no}")
    public Course selectById(@PathVariable("no") String no) {
        return cs.selectById(no);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Response update(@RequestBody Course course) {
        try {
            cs.update(course);
            return new Response(200, "success", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(500, "error", null);
        }
    }

    @RequestMapping("deleteById/{no}")
    public Response deleteById(@PathVariable("no") String no) {
        try {
            cs.deleteById(no);
            return new Response(200, "success", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(500, "error", null);
        }
    }

    @RequestMapping("CourseTableBySno/{sno}")
    public String[][] selectCourseTable(@PathVariable("sno") String sno) {
        List<Course> list = cs.selectCourseTable(sno);
        String[][] array = new String[5][7];
        for (Course item : list) {
            int weekday = 0;
            int ctime = 0;
            switch (item.getWeekday()) {
                case "1":
                    weekday = 0;
                    break;
                case "2":
                    weekday = 1;
                    break;
                case "3":
                    weekday = 2;
                    break;
                case "4":
                    weekday = 3;
                    break;
                case "5":
                    weekday = 4;
                    break;
                case "6":
                    weekday = 5;
                    break;
                case "7":
                    weekday = 6;
                    break;
            }
            ;
            switch (item.getCtime()) {
                case "1":
                    ctime = 0;
                    break;
                case "2":
                    ctime = 1;
                    break;
                case "3":
                    ctime = 2;
                    break;
                case "4":
                    ctime = 3;
                    break;
                case "5":
                    ctime = 4;
                    break;
            }
            ;
            array[ctime][weekday] = item.getNo() + item.getName();
        }
        return array;
    }

}
