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
public class Order {
    private Integer orderId;
    private Date startTime;
    private Date endTime;
    private User user;
    private Room room;
    /**
     * 订单的状态，有未付款，已付款，已取消，已完成，已评价
     */
    private String status;
}
