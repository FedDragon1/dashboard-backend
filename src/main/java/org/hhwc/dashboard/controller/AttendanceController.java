package org.hhwc.dashboard.controller;

import org.hhwc.dashboard.entity.Attendance;
import org.hhwc.dashboard.entity.Course;
import org.hhwc.dashboard.interceptor.annotations.EnsureAdmin;
import org.hhwc.dashboard.interceptor.annotations.EnsureLogin;
import org.hhwc.dashboard.mapper.AttendanceMapper;
import org.hhwc.dashboard.mapper.CourseMapper;
import org.hhwc.dashboard.util.Response;
import org.hhwc.dashboard.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController
public class AttendanceController {
    @Autowired
    private AttendanceMapper attendanceMapper;

    @Autowired
    private CourseMapper courseMapper;

    @GetMapping("/attendance")
    @EnsureAdmin
    public Response<List<Attendance>> getAllAttendances() {
        return ResponseUtil.gather(() -> attendanceMapper.selectAllAttendanceWithName());
    }

    @PostMapping("/attendance")
    @EnsureAdmin
    public Response<Integer> newAttendanceAssignment(Attendance attendance) {
        return ResponseUtil.gather(() -> attendanceMapper.insert(attendance));
    }

    @PutMapping("/attendance")
    @EnsureAdmin
    public Response<Integer> updateAttendance(Attendance attendance) {
        return ResponseUtil.gather(() -> attendanceMapper.updateById(attendance));
    }

    @DeleteMapping("/attendance")
    @EnsureAdmin
    public Response<Integer> deleteAttendance(Attendance attendance) {
        return ResponseUtil.gather(() -> attendanceMapper.deleteById(attendance));
    }

    @GetMapping("/attendance/{attendanceUuid}")
    @EnsureAdmin
    public Response<Attendance> getAttendanceById(@PathVariable String attendanceUuid) {
        return ResponseUtil.gather(() -> attendanceMapper.selectById(attendanceUuid));
    }

    @GetMapping("/attendance/simple")
    @EnsureAdmin
    public Response<List<Attendance>> getAllAttendancesSimple() {
        return ResponseUtil.gather(() -> attendanceMapper.selectList(null));
    }

    private <T> Response<T> authed(Supplier<T> supplier, String instructorUuid, String courseUuid) {
        return ResponseUtil.gather(() -> {
            Course course = courseMapper.selectById(courseUuid);
            if (!instructorUuid.equals(course.getInstructorUuid())) {
                throw new RuntimeException("Unauthorized");
            }
            return supplier.get();
        });
    }

    @GetMapping("/attendance/course/{courseUuid}/{instructorUuid}")
    @EnsureLogin
    public Response<List<Attendance>> getAttendancesByCourse(@PathVariable String courseUuid, @PathVariable String instructorUuid) {
        return authed(() -> attendanceMapper.selectByCourse(courseUuid), instructorUuid, courseUuid);
    }

    @PostMapping("/attendance/course/{instructorUuid}")
    @EnsureLogin
    public Response<Integer> createNewAttendance(@PathVariable String instructorUuid, Attendance attendance) {

        return authed(() -> attendanceMapper.insert(attendance), instructorUuid, attendance.getCourseUuid());
    }

    @PutMapping("/attendance/course/{instructorUuid}")
    @EnsureLogin
    public Response<Integer> updateAttendance(@PathVariable String instructorUuid, Attendance attendance) {
        return authed(() -> attendanceMapper.updateById(attendance), instructorUuid, attendance.getCourseUuid());
    }

    @DeleteMapping("/attendance/course/{instructorUuid}")
    @EnsureLogin
    public Response<Integer> deleteAttendance(@PathVariable String instructorUuid, Attendance attendance) {
        return authed(() -> attendanceMapper.deleteById(attendance), instructorUuid, attendance.getCourseUuid());

    }
}
