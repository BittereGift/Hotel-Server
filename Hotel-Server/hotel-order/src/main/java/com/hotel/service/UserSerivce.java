package com.hotel.service;

import com.hotel.controller.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Bittere_Gift
 */
@FeignClient("service-user")
@RequestMapping("/users")
public interface UserSerivce {

    /**
     * 通过 id 获取用户
     * @param id 用户 id
     * @return 用户
     */
    @GetMapping("/{id}")
    Result getUserById(@PathVariable("id") Integer id);
}
