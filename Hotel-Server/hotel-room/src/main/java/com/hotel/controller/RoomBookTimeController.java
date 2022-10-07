package com.hotel.controller;

import com.hotel.domain.BookTime;
import com.hotel.service.RoomBookTimeService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Bittere_Gift
 */
@RestController
@Slf4j
@RequestMapping("/times")
@Api(tags = "BookTime")
public class RoomBookTimeController {

    @Autowired
    private RoomBookTimeService roomBookTimeService;

    /**
     * 添加预定
     *
     * @param bookTime 预定时间
     */
    @PostMapping
    public Result addBookTime(@RequestBody BookTime bookTime) {
        log.info("bookTime: {}", bookTime);
        boolean flag = roomBookTimeService.add(bookTime);
        Integer code = flag ? Code.POST_OK : Code.POST_FAIL;
        String msg = flag ? "" : "预约添加失败，请重试";
        return new Result(flag, code, msg);
    }

    /**
     * 删除预定
     *
     * @param orderId 预定
     * @return 删除条数
     */
    @DeleteMapping("/{orderId}")
    public Result deleteBookTime(@PathVariable Integer orderId) {
        boolean flag = roomBookTimeService.delete(orderId);
        Integer code = flag? Code.DELETE_OK : Code.DELETE_FAIL;
        String msg = flag? "" : "预约删除失败，请重试";
        return new Result(flag, code, msg);
    }

    /**
     * 更新
     *
     * @param bookTime 预约时间
     * @return 更新条数
     */
    @PutMapping
    public Result updateBookTime(@RequestBody BookTime bookTime) {
        boolean flag = roomBookTimeService.update(bookTime);
        Integer code = flag? Code.PUT_OK : Code.PUT_FAIL;
        String msg = flag? "" : "预约修改失败，请重试";
        return new Result(flag, code, msg);
    }

    /**
     * 通过 orderId 查询
     *
     * @param orderId 订单 id
     * @return 预约时间
     */
    @GetMapping("/o/{orderId}")
    public Result getByOrderId(@PathVariable Integer orderId) {
        BookTime bookTime = roomBookTimeService.getByOrderId(orderId);
        boolean flag = bookTime != null;
        Integer code = flag? Code.GET_OK : Code.GET_FAIL;
        String msg = flag? "" : "预约查询失败，请重试";
        return new Result(bookTime, code, msg);
    }

    /**
     * 通过 roomId 查询
     *
     * @param roomId roomId
     * @return 预约时间
     */
    @GetMapping("/r/{roomId}")
    public Result getByRoomId(@PathVariable Integer roomId) {
        List<BookTime> bookTimes = roomBookTimeService.getByRoomId(roomId);
        boolean flag = bookTimes != null && bookTimes.size() > 0;
        Integer code = flag? Code.GET_OK : Code.GET_FAIL;
        return new Result(bookTimes, code);
    }
}
