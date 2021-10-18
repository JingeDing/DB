package com.example.springboot.controller;

import com.example.springboot.Response.Response;
import com.example.springboot.pojo.Teacher;
import com.example.springboot.service.TeacherService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("teacher")
public class TeacherController {

    @Autowired
    private TeacherService ts;

    /**
     * 分页查询
     * 
     * @param pageNum  页数
     * @param pageSize 每页大小
     * @return
     */
    @RequestMapping("selectAll/{pageNum}/{pageSize}")
    public PageInfo<Teacher> listPage(@PathVariable("pageNum") Integer pageNum,
            @PathVariable("pageSize") Integer pageSize) {
        return ts.selectAllTeacher(pageNum, pageSize);
    }

    @RequestMapping("selectById/{tno}")
    public Teacher selectById(@PathVariable("tno") String tno) {
        return ts.selectById(tno);
    }

    @RequestMapping(value = "addTeacher", method = RequestMethod.POST)
    public Response addTeacher(@RequestBody Teacher teacher) {
        try {
            ts.addTeacher(teacher);
            return new Response(200, "success", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(500, "failure", null);
        }
    }

    @RequestMapping(value = "updateTeacher", method = RequestMethod.POST)
    public Response updateTeacher(@RequestBody Teacher teacher) {
        try {
            ts.updateTeacher(teacher);
            return new Response(200, "success", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(500, "error", null);
        }
    }

    @RequestMapping("deleteById/{tno}")
    public Response deleteById(@PathVariable("tno") String tno) {
        try {
            ts.deleteById(tno);
            return new Response(200, "success", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(500, "error", null);
        }
    }

    @RequestMapping("selectBySno/{sno}")
    public List<Teacher> selectBySno(@PathVariable("sno") String sno) {
        return ts.selectBySno(sno);
    }

}
