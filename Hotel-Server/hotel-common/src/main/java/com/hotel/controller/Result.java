package com.hotel.controller;

import lombok.*;

/**
 * 统一结果返回
 *
 * @author Bittere_Gift
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Object data;
    private Integer code;
    private String message;

    public Result(Object data, Integer code) {
        this.data = data;
        this.code = code;
    }
}
