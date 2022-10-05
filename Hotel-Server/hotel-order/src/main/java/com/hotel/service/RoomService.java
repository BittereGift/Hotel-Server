package com.hotel.service;

import com.hotel.controller.Result;
import com.hotel.exception.BusinessException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author Bittere_Gift
 */
@FeignClient("service-room")
@RequestMapping("/rooms")
public interface RoomService {

    /**
     * 通过 id 更改房间的状态
     *
     * @param id     房间 id
     * @param status 房间的状态
     * @throws BusinessException 如果房间的状态和更改状态一致，抛出异常 ROOM_ALREADY_BOOKED
     */
    @PutMapping("/us")
    void updateStatus(@RequestParam("id") Integer id, @RequestParam("status") Integer status);

    /**
     * 通过 id 查询信息
     *
     * @param id id
     * @return 查询到的对象信息实例
     */
    @GetMapping("/{id}")
    Result getRoomById(@PathVariable Integer id);
}
