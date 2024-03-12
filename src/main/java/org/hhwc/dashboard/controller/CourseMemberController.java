package org.hhwc.dashboard.controller;

import org.hhwc.dashboard.entity.Course;
import org.hhwc.dashboard.entity.CourseMember;
import org.hhwc.dashboard.mapper.CourseMemberMapper;
import org.hhwc.dashboard.util.Response;
import org.hhwc.dashboard.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseMemberController {
    @Autowired
    private CourseMemberMapper courseMemberMapper;

    @GetMapping("/course-member")
    public Response<List<CourseMember>> getAllCourseMember() {
        return ResponseUtil.gather(() -> courseMemberMapper.selectList(null));
    }

    @PostMapping("/course-member")
    public Response<Integer> addNewCourseMember(CourseMember courseMember) {
        return ResponseUtil.gather(() -> courseMemberMapper.insert(courseMember));
    }

    @PutMapping("/course-member")
    public Response<Integer> updateCourseMember(CourseMember courseMember) {
        return ResponseUtil.gather(() -> courseMemberMapper.updateById(
                courseMember.getGrade(), courseMember.getStudentUuid(), courseMember.getCourseUuid()));
    }

    @DeleteMapping("/course-member")
    public Response<Integer> deleteCourseMember(CourseMember courseMember) {
        return ResponseUtil.gather(() -> courseMemberMapper.deleteById(
                courseMember.getStudentUuid(), courseMember.getCourseUuid()));
    }

    @GetMapping("/course-member/student/{studentUuid}")
    public Response<List<CourseMember>> getCoursesOfStudent(@PathVariable String studentUuid) {
        return ResponseUtil.gather(() -> courseMemberMapper.selectMemberByStudentId(studentUuid));
    }
}
