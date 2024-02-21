package org.hhwc.dashboard.controller;

import org.hhwc.dashboard.entity.Instructor;
import org.hhwc.dashboard.interceptor.annotations.EnsureAdmin;
import org.hhwc.dashboard.mapper.InstructorMapper;
import org.hhwc.dashboard.util.Response;
import org.hhwc.dashboard.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InstructorController {
    private static final Logger logger = LoggerFactory.getLogger(InstructorController.class);

    @Autowired
    private InstructorMapper instructorMapper;

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

    @GetMapping("/instructor/simple")
    @EnsureAdmin
    public Response<List<Instructor>> getSimpleInstructors() {
        return ResponseUtil.gather(() -> instructorMapper.selectList(null));
    }
}