package com.hotel.service;

import com.hotel.dao.OrderDao;
import com.hotel.domain.Order;
import com.hotel.domain.Room;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * @author Bittere_Gift
 */
public interface OrderService {


    /**
     * 新增订单
     * @param order 订单
     */
    boolean add(Order order);

    /**
     * 修改订单状态
     *
     * @param orderId   订单 id
     * @param newStatus 新的状态
     * @return 修改成功
     */
    boolean updateStatus(Integer orderId, String newStatus);

    /**
     * 修改订单时间
     *
     * @param orderId   订单 id
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 修改成功
     */
    boolean updateTime(Integer orderId, Date startTime, Date endTime);

    /**
     * 修改订单房间
     *
     * @param orderId 订单 id
     * @param newRoom 房间
     * @return 修改成功
     */
    boolean updateRoom(Integer orderId, Room newRoom);

    /**
     * 通过 id 查询订单
     * @param id id
     * @return 订单
     */
    Order getById(Integer id);

    /**
     * 查询全部
     * @return 订单
     */
    List<Order> getAll();

    /**
     * 查询某个酒店所有的订单
     * @param hotelId 酒店 id
     * @return 订单
     */
    List<Order> getByHotel(Integer hotelId);

    /**
     * 查询某个用户的订单
     * @param userId 用户 id
     * @return 订单
     */
    List<Order> getByUser(Integer userId);

    /**
     * 查询某个用户状态为 status 的订单
     * @param userId 用户 id
     * @param status 状态
     * @return 订单
     */
    List<Order> getByUserAndStatus(Integer userId, String status);

    /**
     * 通过酒店名称模糊查询用户订单
     * @param userId 用户 id
     * @param likeHotelName 模糊的酒店名称
     * @return 订单
     */
    List<Order> getByUserAndLikeHotelName(Integer userId, String likeHotelName);

}
