package com.hotel.service;

import com.hotel.controller.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Bittere_Gift
 */
@FeignClient("service-hotel")
@RequestMapping("/hotels")
public interface HotelService {

    /**
     * 通过 id 查询酒店
     *
     * @param id id
     * @return 酒店
     */
    @GetMapping("/{id}")
    Result getHotelById(@PathVariable Integer id);
}
