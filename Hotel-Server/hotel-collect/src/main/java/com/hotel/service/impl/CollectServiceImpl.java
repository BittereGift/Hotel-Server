package com.hotel.service.impl;

import com.hotel.dao.CollectDao;
import com.hotel.domain.Room;
import com.hotel.domain.User;
import com.hotel.service.CollectService;
import com.hotel.service.RoomService;
import com.hotel.service.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Bittere_Gift
 */
@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectDao collectDao;

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserSerivce userSerivce;

    @Override
    public boolean add(Integer userId, Integer roomId) {
        try {
            collectDao.add(userId, roomId);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Integer userId, Integer roomId) {
        return collectDao.delete(userId, roomId) == 1;
    }

    @Override
    public boolean deleteByUserId(Integer userId) {
        return collectDao.deleteByUserId(userId) != 1;
    }

    @Override
    public boolean deleteByUserAndRooms(Integer userId, List<Integer> roomIds) {
        return collectDao.deleteByUserAndRooms(userId, roomIds) != 0;
    }

    @Override
    public List<Room> getRoomIdsByUserId(Integer userId) {
        List<Integer> ids = collectDao.getRoomIdsByUserId(userId);
        return (List<Room>) roomService.getRoomsByIds(ids).getData();
    }

    @Override
    public Integer getCollectCountOfUser(Integer userId) {
        return collectDao.getCollectCountOfUser(userId);
    }

    @Override
    public List<User> getUserIdsByRoomId(Integer roomId) {
        List<Integer> ids = collectDao.getUserIdsByRoomId(roomId);
        return (List<User>) userSerivce.getUsersByIds(ids).getData();
    }

    @Override
    public Integer getCollectCountOfRoom(Integer roomId) {
        return collectDao.getCollectCountOfRoom(roomId);
    }
}
