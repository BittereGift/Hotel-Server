package com.hotel.service.impl;

import com.hotel.dao.OrderDao;
import com.hotel.domain.Order;
import com.hotel.domain.Room;
import com.hotel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Bittere_Gift
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public boolean add(Order order) {
        try {
            orderDao.add(order);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateStatus(Integer orderId, String newStatus) {
        return orderDao.updateStatus(orderId, newStatus) == 1;
    }

    @Override
    public boolean updateTime(Integer orderId, Date startTime, Date endTime) {
        return orderDao.updateTime(orderId, startTime, endTime) == 1;
    }

    @Override
    public boolean updateRoom(Integer orderId, Room newRoom) {
        return orderDao.updateRoom(orderId, newRoom) == 1;
    }

    @Override
    public Order getById(Integer id) {
        return orderDao.getById(id);
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public List<Order> getByHotel(Integer hotelId) {
        return orderDao.getByHotel(hotelId);
    }

    @Override
    public List<Order> getByUser(Integer userId) {
        return orderDao.getByUser(userId);
    }

    @Override
    public List<Order> getByUserAndStatus(Integer userId, String status) {
        return orderDao.getByUserAndStatus(userId, status);
    }

    @Override
    public List<Order> getByUserAndLikeHotelName(Integer userId, String likeHotelName) {
        if (likeHotelName == null || likeHotelName.isEmpty()) {
            return null;
        }
        return orderDao.getByLikeHotelName(userId, "%" + likeHotelName + "%");
    }
}
