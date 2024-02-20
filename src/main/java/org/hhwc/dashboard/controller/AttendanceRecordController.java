package org.hhwc.dashboard.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.hhwc.dashboard.entity.AttendanceRecord;
import org.hhwc.dashboard.entity.Course;
import org.hhwc.dashboard.interceptor.annotations.EnsureAdmin;
import org.hhwc.dashboard.interceptor.annotations.EnsureLogin;
import org.hhwc.dashboard.mapper.AttendanceMapper;
import org.hhwc.dashboard.mapper.AttendanceRecordMapper;
import org.hhwc.dashboard.mapper.CourseMapper;
import org.hhwc.dashboard.util.Response;
import org.hhwc.dashboard.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController
public class AttendanceRecordController {
    @Autowired
    private AttendanceRecordMapper attendanceRecordMapper;

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Autowired
    private CourseMapper courseMapper;

    @GetMapping("/attendance/record")
    @EnsureAdmin
    public Response<List<AttendanceRecord>> getAllRecords() {
        return ResponseUtil.gather(() -> attendanceRecordMapper.selectList(null));
    }

    private <T> Response<T> authed(Supplier<T> supplier, String instructorUuid, AttendanceRecord record) {
        return ResponseUtil.gather(() -> {
            Course course = courseMapper.selectById(
                    attendanceMapper.selectById(record.getAttendanceUuid()).getCourseUuid());
            if (!course.getInstructorUuid().equals(instructorUuid)) {
                throw new RuntimeException("Unauthorized");
            }
            return supplier.get();
        });
    }

    @GetMapping("/attendance/record/{id}/{instructorUuid}")
    @EnsureLogin
    public Response<AttendanceRecord> getRecordById(@PathVariable Integer id, @PathVariable String instructorUuid) {
        AttendanceRecord record = attendanceRecordMapper.selectById(id);
        return authed(() -> record, instructorUuid, record);
    }

    @PostMapping("/attendance/record/{instructorUuid}")
    @EnsureLogin
    public Response<Integer> newAttendanceEntry(@PathVariable String instructorUuid, AttendanceRecord record) {
        return authed(() -> attendanceRecordMapper.insert(record), instructorUuid, record);
    }

    @PutMapping("/attendance/record/{instructorUuid}")
    public Response<Integer> updateAttendanceEntry(@PathVariable String instructorUuid, AttendanceRecord record) {
        return authed(() -> attendanceRecordMapper.updateById(record), instructorUuid, record);
    }

    @DeleteMapping("/attendance/record/{instructorUuid}")
    public Response<Integer> deleteAttendanceEntry(@PathVariable String instructorUuid, AttendanceRecord record) {
        return authed(() -> attendanceRecordMapper.deleteById(record), instructorUuid, record);
    }
}
