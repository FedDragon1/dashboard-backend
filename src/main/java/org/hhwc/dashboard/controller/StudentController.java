package org.hhwc.dashboard.controller;

import org.hhwc.dashboard.entity.Student;
import org.hhwc.dashboard.interceptor.annotations.EnsureAdmin;
import org.hhwc.dashboard.mapper.StudentMapper;
import org.hhwc.dashboard.util.Response;
import org.hhwc.dashboard.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentMapper studentMapper;

    @GetMapping("/student")
    @EnsureAdmin
    public Response<List<Student>> getAllStudents() {
        return ResponseUtil.gather(() -> studentMapper.selectAllStudentsWithCourses());
    }

    @GetMapping("/student/simple")
    @EnsureAdmin
    public Response<List<Student>> getAllStudentsSimple() {
        return ResponseUtil.gather(() -> studentMapper.selectList(null));
    }
}
