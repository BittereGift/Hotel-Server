package com.hotel.service;

import com.hotel.controller.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Bittere_Gift
 */
@FeignClient("service-order")
@RequestMapping("/orders")
public interface OrderService {

    /**
     * 通过 id 查询订单
     *
     * @param id id
     * @return 订单
     */
    @GetMapping("/{id}")
    Result getOrderById(@PathVariable Integer id);
}
