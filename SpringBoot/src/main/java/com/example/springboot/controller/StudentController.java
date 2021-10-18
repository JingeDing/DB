package com.example.springboot.controller;
import com.example.springboot.Response.Response;
import com.example.springboot.pojo.Student;
import com.example.springboot.service.StudentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@ResponseBody
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService ss;

    @RequestMapping("queryAll")
    public List<Student> queryAll(){
        List<Student> list=ss.queryAll();
        return ss.queryAll();
    }

    @RequestMapping("queryById/{id}")
    public Student queryById(@PathVariable("id") String id){
       return ss.queryById(id);
    }

    /**
     * 分页查询
     * @param pageNum 页数
     * @param pageSize 每页大小
     * @return
     */
    @RequestMapping("selectAllStudent/{pageNum}/{pageSize}")
    public PageInfo<Student> listPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        return ss.studentPageInfo(pageNum,pageSize);
    }

    @RequestMapping(value = "addStudent",method = RequestMethod.POST)
    public Response addStudent(@RequestBody Student student){
        try {
            ss.addStudent(student);
            return new Response(200,"success",null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(500,"error",null);
        }
    }

    @RequestMapping(value = "updateStudent",method = RequestMethod.POST)
    public Response updateStudent(@RequestBody Student student){
        try {
            ss.updateStudent(student);
            return new Response(200,"success",null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(500,"error",null);
        }

    }

    @RequestMapping("deleteById/{sno}")
    public Response deleteById(@PathVariable("sno") String sno){
        try {
            ss.deleteById(sno);
            return new Response(200,"success",null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(500,"error",null);
        }
    }

    @RequestMapping("selectByCno/{cno}")
     public List<Student> selectByCno(@PathVariable("cno") String cno){
        return ss.selectByCno(cno);
     }

}