package com.fjrcloud.sciencepro.exception;

/**
 * Created by liukun on 16/3/10.
 */
public class ApiException extends RuntimeException {

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

}

