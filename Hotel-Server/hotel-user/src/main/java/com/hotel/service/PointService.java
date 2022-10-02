package com.hotel.service;

import com.hotel.controller.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author Bittere_Gift
 */
@FeignClient("service-point")
@RequestMapping("/points")
public interface PointService {

    /**
     * 通过用户id获取积分
     * @param userId userId
     * @return 该用户的积分
     */
    @GetMapping("/{userId}")
    Result getPoint(@PathVariable Integer userId);

    /**
     * 添加用户，并设置初始积分为提供值，如果不设置值，默认为0
     *
     * @param userId 用户id
     * @param point  初始积分
     * @return 添加是否成功
     */
    @PostMapping
    Result addUser(@RequestParam("userId") Integer userId,
                   @RequestParam("point") Integer point);

    /**
     * 删除用户
     *
     * @param userId 用户 id
     * @return 删除成功返回true，否则返回false
     */
    @DeleteMapping("/{userId}")
    Result deleteUser(@PathVariable Integer userId);
}
