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
public class BookTime {
    private Integer roomId;
    private Integer orderId;
    private Date startTime;
    private Date endTime;
}
