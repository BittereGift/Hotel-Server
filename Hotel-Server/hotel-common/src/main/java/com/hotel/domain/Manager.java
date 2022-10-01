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
public class Manager {
    private Integer id;
    private String name;
    private String password;
    private String qq;
    private Hotel hotel;
}
