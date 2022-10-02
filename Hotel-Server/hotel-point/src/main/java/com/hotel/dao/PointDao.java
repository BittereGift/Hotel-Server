package com.hotel.dao;

import org.apache.ibatis.annotations.*;

/**
 * @author Bittere_Gift
 */
@Mapper
public interface PointDao {

    /**
     * 查询用户的积分
     *
     * @param userId 用户 id
     * @return 用户积分
     */
    @Select("select point from point where user_id = #{userId}")
    Integer getPointById(Integer userId);

    /**
     * 更新用户积分
     *
     * @param userId   用户 id
     * @param newPoint 更新后的用户积分
     * @return 更新成功返回1，否则返回0
     */
    @Update("update point set point = #{newPoint} where user_id = #{userId}")
    Integer updatePoint(Integer userId, Integer newPoint);

    /**
     * 删除用户
     *
     * @param userId 用户 id
     * @return 删除成功返回1，否则返回0
     */
    @Delete("delete from point where user_id = #{userId}")
    Integer deleteUser(Integer userId);

    /**
     * 添加用户，自动设置默认积分为0
     *
     * @param userId 用户id
     */
    @Insert("insert into point values (userId, 0)")
    void addUser(Integer userId);

    /**
     * 添加用户，并设置初始积分为提供值
     *
     * @param userId 用户id
     * @param point  初始积分
     */
    @Insert("insert into point values (userId, point)")
    void addUserWithStartPoint(Integer userId, Integer point);

}
