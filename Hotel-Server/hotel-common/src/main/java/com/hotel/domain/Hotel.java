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
public class Hotel {
    private Integer id;
    private String name;
    private String address;
    private String description;
    private List<Room> roomList;
}
