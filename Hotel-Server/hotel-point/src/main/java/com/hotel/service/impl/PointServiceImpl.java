package com.hotel.service.impl;

import com.hotel.dao.PointDao;
import com.hotel.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Bittere_Gift
 */
@Service
public class PointServiceImpl implements PointService {

    @Autowired
    private PointDao pointDao;

    @Override
    public Integer getPointById(Integer userId) {
        return pointDao.getPointById(userId);
    }

    @Override
    public boolean updatePoint(Integer userId, Integer newPoint) {
        return pointDao.updatePoint(userId, newPoint) == 1;
    }

    @Override
    public boolean deleteUser(Integer userId) {
        return pointDao.deleteUser(userId) == 1;
    }

    @Override
    public boolean addUser(Integer userId) {
        try {
            pointDao.addUser(userId);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean addUserWithStartPoint(Integer userId, Integer point) {
        try {
            pointDao.addUserWithStartPoint(userId, point);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
