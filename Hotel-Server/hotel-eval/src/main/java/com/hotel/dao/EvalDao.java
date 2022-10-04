package com.hotel.dao;

import com.hotel.domain.Evaluation;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Bittere_Gift
 */
@Mapper
public interface EvalDao {

    /**
     * 新增评论
     * @param eval 评论
     */
    @Insert("insert into evaluation values (#{order.orderId}, #{order.user.id}, #{order.room.roomId}, " +
            "#{order.room.hotel.id}, #{score}, #{context}, #{pic}, #{video})")
    void add(Evaluation eval);

    /**
     * 删除评论
     * @param orderId 订单 id
     * @return 删除条数
     */
    @Delete("delete from evaluation where order_id = #{orderId}")
    Integer delete(Integer orderId);

    /**
     * 更新评论
     * @param eval 新评论
     * @return 更新条数
     */
    @Update("update evaluation set score = #{score}, context = #{context}, " +
            "pic = #{pic}, video = #{video} where order_id = #{order.orderId}")
    Integer update(Evaluation eval);

    /**
     * 通过订单 id 查询评论
     * @param orderId 订单 id
     * @return 评论
     */
    @Select("select * from evaluation where order_id = #{orderId}")
    @Results(id = "evalMap", value = {
            @Result(property = "order.orderId", column = "order_id"),
            @Result(property = "order.user.id", column = "order_user_id"),
            @Result(property = "order.room.roomId", column = "order_room_id"),
            @Result(property = "order.room.hotel.id", column = "order_hotel_id"),
            @Result(property = "score", column = "score"),
            @Result(property = "context", column = "context"),
            @Result(property = "pic", column = "pic"),
            @Result(property = "video", column = "video")
    })
    Evaluation getById(Integer orderId);

    /**
     * 查询所有评论
     * @return 所有评论
     */
    @Select("select * from evaluation")
    @ResultMap("evalMap")
    List<Evaluation> getAll();

    /**
     * 通过房间号查询评论
     * @param roomId 房间号
     * @return 评论
     */
    @Select("select * from evaluation where order_room_id = #{roomId}")
    @ResultMap("evalMap")
    List<Evaluation> getByRoom(Integer roomId);

    /**
     * 获取某个房间的平均分
     * @param roomId 房价 id
     * @return 平均分
     */
    @Select("select AVG(score) as 'value' from evaluation where order_room_id = #{roomId}")
    @ResultType(Float.class)
    Float getAverageScoreOfRoom(Integer roomId);

    /**
     * 查询某个房间评分低于指定分数的评论
     * @param roomId 房间号
     * @param givenPoint 指定分数
     * @return 评论
     */
    @Select("select * from evaluation where order_room_id = #{roomId} and score <= #{givenPoint}")
    @ResultMap("evalMap")
    List<Evaluation> getScoreLowerThanGivenOfRoom(Integer roomId, Integer givenPoint);

    /**
     * 查询某个房间评分高于指定分数的评论
     * @param roomId 房间号
     * @param givenPoint 指定分数
     * @return 评论
     */
    @Select("select * from evaluation where order_room_id = #{roomId} and score >= #{givenPoint}")
    @ResultMap("evalMap")
    List<Evaluation> getScoreHigherThanGivenOfRoom(Integer roomId, Integer givenPoint);

    /**
     * 通过酒店查询评论
     * @param hotelId 酒店
     * @return 评论
     */
    @Select("select * from evaluation where order_hotel_id = #{hotelId}")
    @ResultMap("evalMap")
    List<Evaluation> getByHotel(Integer hotelId);


    /**
     * 获取某个酒店的平均分
     * @param hotelId 酒店 id
     * @return 平均分
     */
    @Select("select AVG(score) as 'value' from evaluation where order_hotel_id = #{hotelId}")
    @ResultType(Float.class)
    Float getAverageScoreOfHotel(Integer hotelId);

    /**
     * 查询某个酒店评分低于指定分数的评论
     * @param hotelId 酒店号
     * @return 评论
     */
    @Select("select * from evaluation where order_hotel_id = #{hotelId} and score <= #{givenPoint}")
    @ResultMap("evalMap")
    List<Evaluation> getScoreLowerThanGivenOfHotel(Integer hotelId, Integer givenPoint);

    /**
     * 查询某个酒店评分高于指定分数的评论
     * @param hotelId 酒店号
     * @return 评论
     */
    @Select("select * from evaluation where order_hotel_id = #{hotelId} and score >= #{givenPoint}")
    @ResultMap("evalMap")
    List<Evaluation> getScoreHigherThanGivenOfHotel(Integer hotelId, Integer givenPoint);

}
