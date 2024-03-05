package org.hhwc.dashboard.controller;

import org.hhwc.dashboard.entity.Instructor;
import org.hhwc.dashboard.entity.Student;
import org.hhwc.dashboard.interceptor.annotations.EnsureAdmin;
import org.hhwc.dashboard.interceptor.annotations.EnsureLogin;
import org.hhwc.dashboard.mapper.CourseMemberMapper;
import org.hhwc.dashboard.mapper.InstructorMapper;
import org.hhwc.dashboard.util.Response;
import org.hhwc.dashboard.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InstructorController {
    private static final Logger logger = LoggerFactory.getLogger(InstructorController.class);

    @Autowired
    private InstructorMapper instructorMapper;

    @Autowired
    private CourseMemberMapper courseMemberMapper;

    @GetMapping("/instructor")
    @EnsureAdmin
    public Response<List<Instructor>> getAllInstructors() {
        return ResponseUtil.gather(() -> instructorMapper.selectAllInstructors());
    }

    @PostMapping("/instructor")
    @EnsureAdmin
    public Response<Integer> createNewInstructor(Instructor instructor) {
        return ResponseUtil.gather(() -> instructorMapper.insert(instructor));
    }

    @PutMapping("/instructor")
    @EnsureAdmin
    public Response<Integer> updateInstructor(Instructor instructor) {
        return ResponseUtil.gather(() -> instructorMapper.updateById(instructor));
    }

    @DeleteMapping("/instructor")
    @EnsureAdmin
    public Response<Integer> deleteInstructor(Instructor instructor) {
        return ResponseUtil.gather(() -> instructorMapper.deleteById(instructor));
    }

    @GetMapping("/instructor/simple")
    @EnsureAdmin
    public Response<List<Instructor>> getSimpleInstructors() {
        return ResponseUtil.gather(() -> instructorMapper.selectList(null));
    }

    @GetMapping("/instructor/{instructorUuid}")
    @EnsureAdmin
    public Response<Instructor> getInstructorById(@PathVariable String instructorUuid) {
        return ResponseUtil.gather(() -> instructorMapper.selectById(instructorUuid));
    }

    @GetMapping("/instructor/student/{instructorUuid}")
    @EnsureAdmin
    public Response<List<Student>> getStudentsByInstructorUuid(@PathVariable String instructorUuid) {
        return ResponseUtil.gather(() -> courseMemberMapper.selectStudentWithInstructor(instructorUuid));
    }

    @GetMapping("/instructor/{instructorUuid}/{selfUuid}")
    @EnsureLogin
    public Response<Instructor> getSelfInstructor(@PathVariable String instructorUuid, @PathVariable String selfUuid) {
        return ResponseUtil.gather(() -> {
            if (!instructorUuid.equals(selfUuid)) {
                throw new RuntimeException("Unauthorized");
            }
            return instructorMapper.selectById(instructorUuid);
        });
    }
}
