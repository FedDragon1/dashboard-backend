package org.hhwc.dashboard.interceptor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hhwc.dashboard.interceptor.annotations.EnsureAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

public class AdminInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(AdminInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("Entering AdminInterceptor...");

        EnsureAdmin ensureAdmin;
        try {
            ensureAdmin = ((HandlerMethod) handler).getMethodAnnotation(EnsureAdmin.class);
        } catch (Exception e) {
            return true;
        }
        if (ensureAdmin == null) {
            // no annotation exists, pass
            logger.info("Admin not required, pass...");
            return true;
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Dashboard_Admin_UN")) {
                    logger.info("Found Admin Cookie, pass...");
                    return true;
                }
            }
        }

        // 403 Forbidden
        logger.info("Admin cookie is not present, forbidden");
        response.sendError(403);
        return false;
    }
}
