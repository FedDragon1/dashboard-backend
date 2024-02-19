package org.hhwc.dashboard.util;

import lombok.Data;

@Data
public class Response<T> {
    private boolean success = true;
    private String error;
    private T data;
}
