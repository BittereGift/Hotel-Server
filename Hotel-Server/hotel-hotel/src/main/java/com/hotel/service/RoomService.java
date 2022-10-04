package com.hotel.service;

import com.hotel.controller.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author Bittere_Gift
 */
@FeignClient("service-room")
@RequestMapping("/rooms")
public interface RoomService {

    /**
     * 获取该酒店所有的房间
     * @param hotelId 酒店 id
     * @return 房间列表
     */
    @GetMapping("/s")
    Result getRoomList(@RequestParam(value = "hotelId") Integer hotelId);

    /**
     * 删除某个酒店所有的房间
     * @param hotelId 酒店 id
     * @return 是否成功
     */
    @DeleteMapping("/h/{hotelId}")
    Result deleteHotelRooms(@PathVariable Integer hotelId);
}
