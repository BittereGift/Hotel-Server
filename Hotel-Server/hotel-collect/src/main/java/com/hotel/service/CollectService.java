package com.hotel.service;

import com.hotel.dao.CollectDao;
import com.hotel.domain.Room;
import com.hotel.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;

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
     * 删除某个用户的全部收藏
     * @param userId 用户 id
     * @return 移除条数
     */
    boolean deleteByUserId(Integer userId);

    /**
     * 删除某个用户的部分收藏
     * @param userId 用户 id
     * @param roomIds 房间 id
     * @return 删除条数
     */
    boolean deleteByUserAndRooms(Integer userId, List<Integer> roomIds);

    /**
     * 查询某一用户的所有收藏
     * @param userId 用户 id
     * @return 收藏的 roomId
     */
    List<Room> getRoomIdsByUserId(Integer userId);

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
    List<User> getUserIdsByRoomId(Integer roomId);

    /**
     * 查询某个房间的被收藏量
     * @param roomId 房间 id
     * @return 收藏量
     */
    Integer getCollectCountOfRoom(Integer roomId);
}
