package com.hotel.service.impl;

import com.hotel.dao.CollectDao;
import com.hotel.service.CollectService;
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
    public List<Integer> getRoomIdsByUserId(Integer userId) {
        return collectDao.getRoomIdsByUserId(userId);
    }

    @Override
    public Integer getCollectCountOfUser(Integer userId) {
        return collectDao.getCollectCountOfUser(userId);
    }

    @Override
    public List<Integer> getUserIdsByRoomId(Integer roomId) {
        return collectDao.getUserIdsByRoomId(roomId);
    }

    @Override
    public Integer getCollectCountOfRoom(Integer roomId) {
        return collectDao.getCollectCountOfRoom(roomId);
    }
}
