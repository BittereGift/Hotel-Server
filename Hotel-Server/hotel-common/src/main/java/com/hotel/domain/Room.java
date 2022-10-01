package com.hotel.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Bittere_Gift
 */
@Getter
@Setter
@ToString
public class Room {
    private Integer roomId;
    private RoomType type;
    private String position;
    private Hotel hotel;
    /**
     * 房间状态，1为已经被预定，0为空闲房间
     */
    private Integer status;
}
