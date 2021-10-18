package com.example.springboot.controller;

import com.example.springboot.Response.Response;
import com.example.springboot.pojo.Class;
import com.example.springboot.pojo.Department;
import com.example.springboot.service.ClassService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("class")
public class ClassController {

    @Autowired
    private ClassService cs;

    /**
     * 分页查询
     * 
     * @param pageNum  页数
     * @param pageSize 每页大小
     * @return
     */
    @RequestMapping("selectAll/{pageNum}/{pageSize}")
    public PageInfo<Class> listPage(@PathVariable("pageNum") Integer pageNum,
            @PathVariable("pageSize") Integer pageSize) {
        return cs.selectAllClass(pageNum, pageSize);
    }

    @RequestMapping(value = "/addClass", method = RequestMethod.POST)
    public Response add(@RequestBody Class classItem) {
        try {
            cs.addClass(classItem);
            return new Response(200, "success", null);
        } catch (Exception e) {
            return new Response(500, "failure", null);
        }
    }

    @RequestMapping("selectById/{cno}")
    public Class selectById(@PathVariable("cno") String cno) {
        return cs.selectById(cno);
    }

    @RequestMapping(value = "/updateClass", method = RequestMethod.POST)
    public Response update(@RequestBody Class classItem) {
        try {
            cs.updateClass(classItem);
            return new Response(200, "success", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(500, "failure", null);
        }
    }

    @RequestMapping("deleteById/{cno}")
    public Response deleteById(@PathVariable("cno") String cno) {
        try {
            cs.deleteById(cno);
            return new Response(200, "success", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(500, "error", null);
        }
    }

}
