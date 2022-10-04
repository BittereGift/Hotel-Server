package com.hotel.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author Bittere_Gift
 */
@Getter
@Setter
@ToString
public class Status {
    private Integer orderId;
    private String info;
    private Date changeTime;
}
