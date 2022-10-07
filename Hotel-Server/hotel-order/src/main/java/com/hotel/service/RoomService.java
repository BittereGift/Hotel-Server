package com.hotel.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotel.controller.Result;
import com.hotel.domain.BookTime;
import com.hotel.exception.BusinessException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author Bittere_Gift
 */
@FeignClient("service-room")
public interface RoomService {
    /**
     * 通过 id 查询信息
     *
     * @param id id
     * @return 查询到的对象信息实例
     */
    @GetMapping("/rooms/{id}")
    Result getRoomById(@PathVariable Integer id);

    /**
     * 通过 roomId 查询
     *
     * @param roomId roomId
     * @return 预约时间
     */
    @GetMapping("/times/r/{roomId}")
    Result getBookTimeByRoomId(@PathVariable Integer roomId);

    /**
     * 添加预定
     *
     * @param bookTime 预定时间
     */
    @PostMapping("/times")
    Result addBookTime(@RequestBody @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd") BookTime bookTime);
}
