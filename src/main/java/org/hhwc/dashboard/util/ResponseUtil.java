package org.hhwc.dashboard.util;


import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

public class ResponseUtil {
    private static final Logger logger = LoggerFactory.getLogger(ResponseUtil.class);

    public static <T> Response<T> getResponse() {
        return new Response<>();
    }

    public static <T> Response<T> gather(Supplier<T> supplier) {
        Response<T> response = getResponse();
        try {
            response.setData(supplier.get());
            logger.debug("Successfully acquired data...");
        } catch (Exception e) {
            String stackTrace = e.getMessage();
            response.setSuccess(false);
            response.setError(stackTrace);
            logger.debug("Failed to acquire data!");
            logger.debug(stackTrace);
        }
        return response;
    }
}
