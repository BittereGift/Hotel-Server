package com.hotel.dao;

import com.hotel.domain.BookTime;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Bittere_Gift
 */
@Mapper
public interface RoomBookTimeDao {

    /**
     * 添加预定
     *
     * @param bookTime 预定时间
     */
    @Insert("insert into room_book_time values (#{orderId}, #{roomId}, #{startTime}, #{endTime})")
    void add(BookTime bookTime);

    /**
     * 删除预定
     *
     * @param orderId 预定
     * @return 删除条数
     */
    @Delete("delete from room_book_time where order_id = #{orderId}")
    Integer delete(Integer orderId);

    /**
     * 更新
     *
     * @param bookTime 预约时间
     * @return 更新条数
     */
    @Update("update room_book_time set room_id = #{roomId}, start_time = #{startTime}, " +
            "end_time = #{endTime} where order_id = #{orderId}")
    Integer update(BookTime bookTime);

    /**
     * 通过 orderId 查询
     *
     * @param orderId 订单 id
     * @return 预约时间
     */
    @Select("select * from room_book_time where order_id = #{orderId}")
    @Results(id = "BookTimeMap", value = {
            @Result(property = "startTime", column = "start_time"),
            @Result(property = "endTime", column = "end_time"),
            @Result(property = "roomId", column = "room_id"),
            @Result(property = "orderId", column = "order_id")
    })
    BookTime getByOrderId(Integer orderId);

    /**
     * 通过 roomId 查询
     *
     * @param roomId roomId
     * @return 预约时间
     */
    @Select("select * from room_book_time where room_id = #{roomId} order by start_time")
    @ResultMap("BookTimeMap")
    List<BookTime> getByRoomId(Integer roomId);
}
