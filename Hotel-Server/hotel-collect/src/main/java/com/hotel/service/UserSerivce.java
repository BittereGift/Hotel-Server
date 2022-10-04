package com.hotel.service;

import com.hotel.controller.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Bittere_Gift
 */
@FeignClient("service-user")
@RequestMapping("/users")
public interface UserSerivce {

    /**
     * 通过 ids 获取用户列表
     * @param ids 用户 id
     * @return 用户列表
     */
    @GetMapping("/ids")
    Result getUsersByIds(@RequestParam("ids") List<Integer> ids);
}
