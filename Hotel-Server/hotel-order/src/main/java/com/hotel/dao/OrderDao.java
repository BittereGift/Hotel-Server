package com.hotel.dao;

import com.hotel.domain.Order;
import com.hotel.domain.Room;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.Date;
import java.util.List;

/**
 * @author Bittere_Gift
 */
@Mapper
public interface OrderDao {

    /**
     * 新增订单
     *
     * @param order 订单
     */
    @InsertProvider(value = OrderSqlProvider.class, method = "add")
    @Options(useGeneratedKeys = true, keyProperty = "orderId")
    void add(Order order);

    /**
     * 修改订单状态
     *
     * @param orderId   订单 id
     * @param newStatus 新的状态
     * @return 修改行数
     */
    @Update("update order_order set status = #{newStatus} where order_id = #{orderId}")
    Integer updateStatus(Integer orderId, String newStatus);

    /**
     * 修改订单时间
     *
     * @param orderId   订单 id
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 修改行数
     */
    @Update("update order_order set startTime = #{startTime}, endTime = #{endTime} where order_id = #{orderId}")
    Integer updateTime(Integer orderId, Date startTime, Date endTime);

    /**
     * 修改订单房间
     *
     * @param orderId 订单 id
     * @param newRoom 房间
     * @return 修改行数
     */
    @Update("update order_order set room_id = #{newRoom.id}, room_position = #{newRoom.position}, " +
            "room_type_id = #{newRoom.type.id}, room_type_name = #{newRoom.type.name}, " +
            "room_type_price = #{newRoom.type.price} where order_id = #{orderId}")
    Integer updateRoom(Integer orderId, Room newRoom);

    /**
     * 通过 id 查询订单
     *
     * @param id id
     * @return 订单
     */
    @Select("select * from order_order where id = #{id}")
    @Results(id = "orderMap", value = {
            @Result(column = "id", property = "orderId"),
            @Result(column = "start_time", property = "startTime"),
            @Result(column = "end_time", property = "endTime"),
            @Result(column = "status", property = "status"),
            @Result(column = "user_id", property = "user.id"),
            @Result(column = "user_name", property = "user.name"),
            @Result(column = "user_tele", property = "user.tele"),
            @Result(column = "room_id", property = "room.roomId"),
            @Result(column = "room_type_id", property = "room.type.id"),
            @Result(column = "room_type_name", property = "room.type.name"),
            @Result(column = "room_type_price", property = "room.type.price"),
            @Result(column = "room_position", property = "room.position"),
            @Result(column = "hotel_id", property = "room.hotel.id"),
            @Result(column = "hotel_name", property = "room.hotel.name"),
            @Result(column = "hotel_address", property = "room.hotel.address")
    })
    Order getById(Integer id);

    /**
     * 查询全部
     *
     * @return 订单
     */
    @Select("select * from order_order")
    @ResultMap("orderMap")
    List<Order> getAll();

    /**
     * 查询某个酒店所有的订单
     *
     * @param hotelId 酒店 id
     * @return 订单
     */
    @Select("select * from order_order where hotel_id = #{hotelId}")
    @ResultMap("orderMap")
    List<Order> getByHotel(Integer hotelId);

    /**
     * 查询某个用户的订单
     *
     * @param userId 用户 id
     * @return 订单
     */
    @Select("select * from order_order where user_id = #{userId}")
    @ResultMap("orderMap")
    List<Order> getByUser(Integer userId);

    /**
     * 查询某个用户状态为 status 的订单
     *
     * @param userId 用户 id
     * @param status 状态
     * @return 订单
     */
    @Select("select * from order_order where user_id = #{userId} and status = #{status}")
    @ResultMap("orderMap")
    List<Order> getByUserAndStatus(Integer userId, String status);

    /**
     * 通过酒店名称模糊查询用户订单
     *
     * @param userId        用户 id
     * @param likeHotelName 模糊的酒店名称
     * @return 订单
     */
    @Select("select * from order_order where user_id = #{userId} and hotel_name like #{likeHotelName}")
    @ResultMap("orderMap")
    List<Order> getByLikeHotelName(Integer userId, String likeHotelName);

    class OrderSqlProvider {
        public String add(Order order) {
            return new SQL() {{
                INSERT_INTO("order_order");
                VALUES("start_time", "#{startTime}");
                VALUES("end_time", "#{endTime}");
                VALUES("user_id", "#{user.id}");
                VALUES("user_name", "#{user.name}");
                VALUES("user_tele", "#{user.tele}");
                VALUES("room_id", "#{room.roomId}");
                VALUES("room_type_id", "#{room.type.id}");
                VALUES("room_type_name", "#{room.type.name}");
                VALUES("room_type_price", "#{room.type.price}");
                VALUES("room_position", "#{room.position}");
                VALUES("hotel_id", "#{room.hotel.id}");
                VALUES("hotel_name", "#{room.hotel.name}");
                VALUES("hotel_address", "#{room.hotel.address}");
                VALUES("status", "#{status}");
            }}.toString();
        }
    }
}
