package org.hhwc.dashboard.controller;

import org.hhwc.dashboard.entity.Student;
import org.hhwc.dashboard.interceptor.annotations.EnsureAdmin;
import org.hhwc.dashboard.mapper.CourseMemberMapper;
import org.hhwc.dashboard.mapper.StudentMapper;
import org.hhwc.dashboard.util.Response;
import org.hhwc.dashboard.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/student")
    @EnsureAdmin
    public Response<Integer> newStudent(Student student) {
        return ResponseUtil.gather(() -> studentMapper.insert(student));
    }

    @PutMapping("/student")
    @EnsureAdmin
    public Response<Integer> updateStudent(Student student) {
        System.out.println(student);
        return ResponseUtil.gather(() -> studentMapper.updateById(student));
    }

    @DeleteMapping("/student")
    @EnsureAdmin
    public Response<Integer> deleteStudent(Student student) {
        return ResponseUtil.gather(() -> studentMapper.deleteById(student));
    }

    @GetMapping("/student/{studentUuid}")
    @EnsureAdmin
    public Response<Student> getStudentById(@PathVariable String studentUuid) {
        return ResponseUtil.gather(() -> studentMapper.selectById(studentUuid));
    }

    @GetMapping("/student/course/{courseUuid}")
    @EnsureAdmin
    public Response<List<Student>> getStudentsWithCourse(@PathVariable String courseUuid) {
        return ResponseUtil.gather(() -> courseMemberMapper.selectStudentByCourseId(courseUuid));
    }

    @GetMapping("/student/instructor/{instructorUuid}")
    @EnsureAdmin
    public Response<List<Student>> getStudentsByInstructor(@PathVariable String instructorUuid) {
        return ResponseUtil.gather(() -> courseMemberMapper.selectStudentWithInstructor(instructorUuid));
    }

    @GetMapping("/student/simple")
    @EnsureAdmin
    public Response<List<Student>> getAllStudentsSimple() {
        return ResponseUtil.gather(() -> studentMapper.selectList(null));
    }
}
