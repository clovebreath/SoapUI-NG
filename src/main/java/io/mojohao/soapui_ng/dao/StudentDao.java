package io.mojohao.soapui_ng.dao;

import io.mojohao.soapui_ng.entity.Student;

import java.util.List;

public interface StudentDao {
    List<Student> findAllStudents();
//    Student findStudentById(Integer id);
//    void insertStudent(Student student);
}
