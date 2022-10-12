package com.hotel.service;

import com.hotel.controller.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Bittere_Gift
 */
@FeignClient("service-collect")
@RequestMapping("/collects")
public interface CollectService {

    /**
     * 删除某个用户的全部收藏
     * @param userId
     */
    @DeleteMapping("/de/{userId}")
    void deleteCollectsByUser(@PathVariable Integer userId);
}
