package io.mojohao.soapui_ng.controller;

import io.mojohao.soapui_ng.entity.Student;
import io.mojohao.soapui_ng.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/hello")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @ResponseBody
    @RequestMapping(value = "/allStudents",method = RequestMethod.GET)
    public List<Student> getAllStudent(){
        return studentService.findAllStudents();
    }
    @RequestMapping(value = "/allStudentssss",method = RequestMethod.GET)
    public String getAllStudentss(){
        return "hello";
    }
}
