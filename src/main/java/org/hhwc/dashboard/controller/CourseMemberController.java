package org.hhwc.dashboard.controller;

import org.hhwc.dashboard.entity.Course;
import org.hhwc.dashboard.interceptor.annotations.EnsureLogin;
import org.hhwc.dashboard.mapper.CourseMemberMapper;
import org.hhwc.dashboard.util.Response;
import org.hhwc.dashboard.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseMemberController {
    @Autowired
    private CourseMemberMapper courseMemberMapper;

    @GetMapping("/course-member/student/{studentUuid}")
    public Response<List<Course>> getCoursesOfStudent(@PathVariable String studentUuid) {
        return ResponseUtil.gather(() -> courseMemberMapper.selectCourseWithStudentId(studentUuid));
    }
}
