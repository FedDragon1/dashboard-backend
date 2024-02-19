package org.hhwc.dashboard.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.hhwc.dashboard.entity.Course;
import org.hhwc.dashboard.interceptor.annotations.EnsureAdmin;
import org.hhwc.dashboard.interceptor.annotations.EnsureLogin;
import org.hhwc.dashboard.util.Response;
import org.hhwc.dashboard.mapper.CourseMapper;
import org.hhwc.dashboard.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {
    private static final Logger logger = LoggerFactory.getLogger(InstructorController.class);

    @Autowired
    private CourseMapper courseMapper;

    @GetMapping("/courses")
    @EnsureAdmin
    public Response<List<Course>> getAllCourses() {
        return ResponseUtil.gather(() -> courseMapper.selectAll());
    }

    @PostMapping("/courses")
    @EnsureAdmin
    public Response<Integer> newCourse(Course course) {
        return ResponseUtil.gather(() -> courseMapper.insert(course));
    }

    @PutMapping("/courses")
    @EnsureAdmin
    public Response<Integer> updateCourse(Course course) {
        return ResponseUtil.gather(() -> courseMapper.updateById(course));
    }

    @DeleteMapping("/courses")
    @EnsureAdmin
    public Response<Integer> deleteCourse(Course course) {
        return ResponseUtil.gather(() -> courseMapper.deleteById(course));
    }

    @GetMapping("/courses/simple")
    @EnsureAdmin
    public Response<List<Course>> getAllCoursesSimple() {
        return ResponseUtil.gather(() -> courseMapper.selectList(null));
    }

    @GetMapping("/courses/instructor/{uuid}")
    @EnsureLogin
    public Response<List<Course>> getCoursesByInstructor(@PathVariable String uuid) {
        return ResponseUtil.gather(() -> courseMapper.selectByInstructor(uuid));
    }
}
