package org.hhwc.dashboard.interceptor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hhwc.dashboard.interceptor.annotations.EnsureAdmin;
import org.hhwc.dashboard.interceptor.annotations.EnsureLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("Entering LoginInterceptor...");

        EnsureLogin ensureLogin = ((HandlerMethod) handler).getMethodAnnotation(EnsureLogin.class);
        if (ensureLogin == null) {
            logger.info("Login not required, pass...");
            return true;
        }

        String[] split = request.getRequestURI().split("/");
        String pathArg = split[split.length - 1];

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                // Ensure pathArg is same as cookie
                if (cookie.getName().equals("Dashboard_Instructor") && pathArg.equals(cookie.getValue())) {
                    logger.info("Found Login Cookie, pass...");
                    return true;
                }
            }
        }

        // 403 Forbidden
        logger.info("Login cookie is not present, forbidden");
        response.sendError(403);
        return false;
    }
}
