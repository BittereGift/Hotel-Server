package com.hotel.service;

/**
 * @author Bittere_Gift
 */
public interface PointService {

    /**
     * 查询用户的积分
     *
     * @param userId 用户 id
     * @return 用户积分
     */
    Integer getPointById(Integer userId);

    /**
     * 更新用户积分
     *
     * @param userId   用户 id
     * @param newPoint 更新后的用户积分
     * @return 更新成功返回true，否则返回false
     */
    boolean updatePoint(Integer userId, Integer newPoint);

    /**
     * 删除用户
     *
     * @param userId 用户 id
     * @return 删除成功返回true，否则返回false
     */
    boolean deleteUser(Integer userId);

    /**
     * 添加用户，并设置初始积分为提供值
     *
     * @param userId 用户id
     * @param point  初始积分
     * @return 添加是否成功
     */
    boolean addUserWithStartPoint(Integer userId, Integer point);
}
