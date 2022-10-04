package com.hotel.service;

import java.util.List;

/**
 * @author Bittere_Gift
 */
public interface CollectService {

    /**
     * 新收藏
     * @param userId 用户 id
     * @param roomId 房间 id
     * @return 是否成功
     */
    boolean add(Integer userId, Integer roomId);

    /**
     * 移除收藏
     * @param userId 用户 id
     * @param roomId 房间 id
     * @return 是否成功
     */
    boolean delete(Integer userId, Integer roomId);

    /**
     * 查询某一用户的所有收藏
     * @param userId 用户 id
     * @return 收藏的 roomId
     */
    List<Integer> getRoomIdsByUserId(Integer userId);

    /**
     * 查询某一用户的收藏量
     * @param userId 用户 id
     * @return 收藏量
     */
    Integer getCollectCountOfUser(Integer userId);

    /**
     * 查询某一个房间被哪些客户收藏
     * @param roomId 房间 id
     * @return 客户 id
     */
    List<Integer> getUserIdsByRoomId(Integer roomId);

    /**
     * 查询某个房间的被收藏量
     * @param roomId 房间 id
     * @return 收藏量
     */
    Integer getCollectCountOfRoom(Integer roomId);
}
