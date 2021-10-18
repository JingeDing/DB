package com.example.springboot.controller;

import com.example.springboot.Response.Response;
import com.example.springboot.pojo.Department;
import com.example.springboot.service.DepartmentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("department")
public class DepartmentController {

    @Autowired
    private DepartmentService ds;

    /**
     * 分页查询
     * 
     * @param pageNum  页数
     * @param pageSize 每页大小
     * @return
     */
    @RequestMapping("selectAllDepartment/{pageNum}/{pageSize}")
    public PageInfo<Department> listPage(@PathVariable("pageNum") Integer pageNum,
            @PathVariable("pageSize") Integer pageSize) {
        return ds.departmentPageInfo(pageNum, pageSize);
    }

    @RequestMapping(value = "/addDepartment", method = RequestMethod.POST)
    public Response add(@RequestBody Department department) {
        try {
            ds.addDepartment(department);
            return new Response(200, "success", null);
        } catch (Exception e) {
            return new Response(500, "failure", null);
        }
    }

    @RequestMapping("selectById/{dno}")
    public Department selectById(@PathVariable("dno") String dno) {
        return ds.selectById(dno);
    }

    @RequestMapping(value = "/updateDepartment", method = RequestMethod.POST)
    public Response update(@RequestBody Department department) {
        try {
            ds.updateDepartment(department);
            return new Response(200, "success", null);
        } catch (Exception e) {
            return new Response(500, "failure", null);
        }
    }

    @RequestMapping("deleteById/{dno}")
    public Response deleteById(@PathVariable("dno") String dno) {
        try {
            ds.deleteById(dno);
            return new Response(200, "success", null);
        } catch (Exception e) {
            return new Response(500, "failure", null);
        }
    }

}
