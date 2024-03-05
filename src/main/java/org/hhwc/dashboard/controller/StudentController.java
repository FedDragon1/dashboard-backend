package org.hhwc.dashboard.controller;

import org.hhwc.dashboard.entity.Student;
import org.hhwc.dashboard.interceptor.annotations.EnsureAdmin;
import org.hhwc.dashboard.mapper.CourseMemberMapper;
import org.hhwc.dashboard.mapper.StudentMapper;
import org.hhwc.dashboard.util.Response;
import org.hhwc.dashboard.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private CourseMemberMapper courseMemberMapper;

    @GetMapping("/student")
    @EnsureAdmin
    public Response<List<Student>> getAllStudents() {
        return ResponseUtil.gather(() -> studentMapper.selectAllStudentsWithCourses());
    }

    @GetMapping("/student/course/{courseUuid}")
    @EnsureAdmin
    public Response<List<Student>> getStudentsWithCourse(@PathVariable String courseUuid) {
        return ResponseUtil.gather(() -> courseMemberMapper.selectStudentWithInstructor(courseUuid));
    }

        @GetMapping("/student/simple")
        @EnsureAdmin
        public Response<List<Student>> getAllStudentsSimple() {
            return ResponseUtil.gather(() -> studentMapper.selectList(null));
        }
}
