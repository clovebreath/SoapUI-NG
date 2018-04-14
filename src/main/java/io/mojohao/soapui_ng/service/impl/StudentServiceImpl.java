package io.mojohao.soapui_ng.service.impl;

import io.mojohao.soapui_ng.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentDao studentDao;

    @Override
    public List<Student> findAllStudents() {
       return studentDao.findAllStudents();
    }
}
