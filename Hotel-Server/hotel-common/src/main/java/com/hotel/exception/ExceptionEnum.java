package com.hotel.exception;

import lombok.Getter;

/**
 * @author Bittere_Gift
 */

@Getter
public enum ExceptionEnum {

    //房间已被使用
    ROOM_ALREADY_BOOKED(500, "房间已被使用");

    /**
     * 状态码
     */
    private int status;
    /**
     * 异常信息
     */
    private String msg;

    ExceptionEnum(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
