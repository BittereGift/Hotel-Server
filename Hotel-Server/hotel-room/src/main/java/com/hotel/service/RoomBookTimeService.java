package com.hotel.service;

import com.hotel.domain.BookTime;

import java.util.List;

/**
 * @author Bittere_Gift
 */
public interface RoomBookTimeService {

    /**
     * 添加预定
     *
     * @param bookTime 预定时间
     */
    boolean add(BookTime bookTime);

    /**
     * 删除预定
     *
     * @param orderId 预定
     * @return 删除条数
     */
    boolean delete(Integer orderId);

    /**
     * 更新
     *
     * @param bookTime 预约时间
     * @return 更新条数
     */
    boolean update(BookTime bookTime);

    /**
     * 通过 orderId 查询
     *
     * @param orderId 订单 id
     * @return 预约时间
     */
    BookTime getByOrderId(Integer orderId);

    /**
     * 通过 roomId 查询
     *
     * @param roomId roomId
     * @return 预约时间
     */
    List<BookTime> getByRoomId(Integer roomId);
}
