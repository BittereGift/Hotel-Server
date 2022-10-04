package com.hotel.dao;

import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Bittere_Gift
 */
@Mapper
public interface CollectDao {

    /**
     * 新收藏
     * @param userId 用户 id
     * @param roomId 房间 id
     */
    @Insert("insert into collect values (#{userId}, #{roomId})")
    void add(Integer userId, Integer roomId);

    /**
     * 移除收藏
     * @param userId 用户 id
     * @param roomId 房间 id
     * @return 移除条数
     */
    @Delete("delete from collect where user_id = #{userId} and room_id = #{roomId}")
    Integer delete(Integer userId, Integer roomId);

    /**
     * 查询某一用户的所有收藏
     * @param userId 用户 id
     * @return 收藏的 roomId
     */
    @Select("select room_id as 'value' from collect where user_id = #{userId}")
    @ResultType(Integer.class)
    List<Integer> getRoomIdsByUserId(Integer userId);

    /**
     * 查询某一用户的收藏量
     * @param userId 用户 id
     * @return 收藏量
     */
    @Select("select count(*) as 'value' from collect where user_id = #{userId}")
    @ResultType(Integer.class)
    Integer getCollectCountOfUser(Integer userId);

    /**
     * 查询某一个房间被哪些客户收藏
     * @param roomId 房间 id
     * @return 客户 id
     */
    @Select("select user_id as 'value' from collect where room_id = #{roomId}")
    @ResultType(Integer.class)
    List<Integer> getUserIdsByRoomId(Integer roomId);

    /**
     * 查询某个房间的被收藏量
     * @param roomId 房间 id
     * @return 收藏量
     */
    @Select("select count(*) as 'value' from collect where room_id = #{roomId}")
    @ResultType(Integer.class)
    Integer getCollectCountOfRoom(Integer roomId);
}
