package com.hotel.service;

import com.hotel.domain.Status;

import java.util.Date;
import java.util.List;

/**
 * @author Bittere_Gift
 */
public interface LogService {
    /**
     * 新增日志
     *
     * @param orderId    订单 id
     * @param info       日志信息
     */
    boolean add(Integer orderId, String info);

    /**
     * 通过订单 id 查询日志
     *
     * @param orderId 订单 id
     * @return 日志
     */
    List<Status> getLogsByOrder(Integer orderId);
}
