package com.hotel.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

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
    private List<BookTime> bookTimeList;
}
