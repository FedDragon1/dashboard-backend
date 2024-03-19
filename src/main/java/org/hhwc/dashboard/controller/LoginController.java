package org.hhwc.dashboard.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hhwc.dashboard.entity.Admin;
import org.hhwc.dashboard.entity.Instructor;
import org.hhwc.dashboard.mapper.AdminMapper;
import org.hhwc.dashboard.mapper.InstructorMapper;
import org.hhwc.dashboard.util.EntityUtil;
import org.hhwc.dashboard.util.Response;
import org.hhwc.dashboard.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Objects;

@RestController
public class LoginController {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private InstructorMapper instructorMapper;

    @Autowired
    private HttpServletResponse response;

    @Value("${dashboard.cookie.max-life}")
    private Integer cookieMaxLife;

    @Value("${dashboard.cookie.domain}")
    private String domain;

    private Cookie getCookie(String name, String value){
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(cookieMaxLife);
        cookie.setSecure(true);
        cookie.setDomain(domain);
        cookie.setPath("/");
        return cookie;
    }

    @PostMapping("/login/admin")
    public Response<String> login(Admin admin,
                                  @CookieValue(value = "Dashboard_Admin_UN", defaultValue = "") String cookieName) {
        return ResponseUtil.gather(() -> {
            if (!cookieName.isEmpty()) {
                return cookieName;
            }

            Admin thisAdmin = adminMapper.selectById(admin.getUsername());
            if (thisAdmin == null || !thisAdmin.getPassword().equals(admin.getPassword())) {
                throw new RuntimeException("Invalid Credential");
            }

            Cookie cookie = getCookie("Dashboard_Admin_UN", thisAdmin.getUsername());
            response.addCookie(cookie);

            return thisAdmin.getUsername();
        });
    }

    @PostMapping("/login/instructor")
    public Response<Instructor> login(Instructor instructor,
                                      @CookieValue(value = "Dashboard_Instructor", defaultValue = "") String cookieName) {
        return ResponseUtil.gather(() -> {

            Instructor thisInstructor;

            if (EntityUtil.allNull(instructor) && !cookieName.isEmpty()) {
                thisInstructor = instructorMapper.selectInstructorAndCoursesById(cookieName);
            } else {
                thisInstructor = instructorMapper.selectInstructorAndCoursesById(instructor.getUuid());
            }

            if (thisInstructor == null || (Objects.nonNull(instructor.getPassword()) &&
                    !thisInstructor.getPassword().equals(instructor.getPassword()))) {
                throw new RuntimeException("Invalid Credential");
            }

            Cookie cookie = getCookie("Dashboard_Instructor", thisInstructor.getUuid());
            response.addCookie(cookie);

            // TODO see this
            cookie = getCookie("Dashboard_Admin_UN", "admin");
            response.addCookie(cookie);

            return thisInstructor;
        });
    }
}
