package com.hotel.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

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
     * 添加用户，并设置初始积分为提供值
     *
     * @param userId 用户id
     * @param point  初始积分
     */
    @InsertProvider(value = PointSqlProvider.class, method = "addUser")
    void addUserWithStartPoint(@Param("userId") Integer userId, @Param("point") Integer point);

    class PointSqlProvider {
        public String addUser(Map<String,Object> para) {
            return new SQL() {{
                Integer userId = (Integer) para.get("userId");
                INSERT_INTO("point");
                VALUES("user_id", "#{userId}");
                if (para.get("point") != null) {
                    VALUES("point", "#{point}");
                } else {
                    VALUES("point", "0");
                }
            }}.toString();
        }
    }

}
