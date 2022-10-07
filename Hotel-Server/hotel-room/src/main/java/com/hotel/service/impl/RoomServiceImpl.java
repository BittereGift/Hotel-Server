package com.hotel.service.impl;

import com.hotel.dao.RoomDao;
import com.hotel.domain.Hotel;
import com.hotel.domain.Room;
import com.hotel.exception.BusinessException;
import com.hotel.exception.ExceptionEnum;
import com.hotel.service.HotelService;
import com.hotel.service.RoomService;
import com.hotel.service.overwrite.HotelServiceOverwrite;
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

    @Autowired
    private HotelServiceOverwrite hotelService;

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
    public boolean deleteByHotel(Integer hotelId) {
        return roomDao.deleteByHotelId(hotelId) != 0;
    }

    @Override
    public boolean update(Room room) {
        return roomDao.update(room) == 1;
    }

    @Override
    public Room getById(Integer id) {
        Room room = roomDao.getById(id);
        Hotel hotel = hotelService.getHotelById(room.getHotel().getId());
        room.setHotel(hotel);
        return room;
    }

    @Override
    public List<Room> getAll() {
        return roomDao.getAll();
    }

    @Override
    public List<Room> getByIds(List<Integer> ids) {
        return roomDao.getByIds(ids);
    }

    @Override
    public List<Room> getByHotelAndTypeAndStatusAndPosition(Integer hotelId, Integer typeId, Integer status, String position) {
        return roomDao.getByHotelAndTypeAndStatusAndPosition(hotelId, typeId, status, position);
    }

}
