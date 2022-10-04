package com.hotel.service;

import com.hotel.controller.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Bittere_Gift
 */
@FeignClient("service-room")
@RequestMapping("/rooms")
public interface RoomService {

    /**
     * 通过 id 数组获取 room 集合
     *
     * @param ids ids
     * @return rooms
     */
    @GetMapping("/ids")
    Result getRoomsByIds(@RequestParam("ids") List<Integer> ids);
}
