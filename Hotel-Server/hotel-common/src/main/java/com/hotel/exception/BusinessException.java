package com.hotel.exception;

/**
 * @author Bittere_Gift
 */
public class BusinessException extends RuntimeException {

    private Integer status;
    private String msg;

    public BusinessException(Integer status, String msg) {
        super(msg);
        this.status = status;
        this.msg = msg;
    }

    public BusinessException(ExceptionEnum exception) {
        super(exception.getMsg());
        this.status = exception.getStatus();
        this.msg = exception.getMsg();
    }
}
