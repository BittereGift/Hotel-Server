package com.hotel.service.impl;

import com.hotel.dao.RoomDao;
import com.hotel.domain.Room;
import com.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Bittere_Gift
 */
@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomDao roomDao;

    @Override
    public boolean addRoom(Room room) {
        try {
            roomDao.add(room);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteById(Integer id) {
        return roomDao.deleteById(id) == 1;
    }

    @Override
    public boolean update(Room room) {
        return roomDao.update(room) == 1;
    }

    @Override
    public boolean updateStatus(Integer id, Integer status) {
        return roomDao.updateStatus(id, status) == 1;
    }

    @Override
    public Room getById(Integer id) {
        return roomDao.getById(id);
    }

    @Override
    public List<Room> getAll() {
        return roomDao.getAll();
    }

    @Override
    public List<Room> getByHotelId(Integer hotelId) {
        return roomDao.getByHotelId(hotelId);
    }

    @Override
    public List<Room> getByType(Integer typeId) {
        return roomDao.getByType(typeId);
    }

    @Override
    public List<Room> getByHotelAndType(Integer hotelId, Integer typeId) {
        return roomDao.getByHotelAndType(hotelId, typeId);
    }

    @Override
    public List<Room> getByHotelAndStatus(Integer hotelId, Integer status) {
        return roomDao.getByHotelAndStatus(hotelId, status);
    }

    @Override
    public List<Room> getByHotelAndTypeAndStatus(Integer hotelId, Integer typeId, Integer status) {
        return roomDao.getByHotelAndTypeAndStatus(hotelId, typeId, status);
    }

    @Override
    public Room getByHotelAndPosition(Integer hotelId, String position) {
        return roomDao.getByHotelAndPosition(hotelId, position);
    }
}
