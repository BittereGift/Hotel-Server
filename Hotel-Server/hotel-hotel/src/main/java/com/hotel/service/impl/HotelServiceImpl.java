package com.hotel.service.impl;

import com.hotel.controller.Result;
import com.hotel.dao.HotelDao;
import com.hotel.domain.Hotel;
import com.hotel.domain.Room;
import com.hotel.service.HotelService;
import com.hotel.service.RoomService;
import com.hotel.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Bittere_Gift
 */
@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelDao hotelDao;

    @Autowired
    private RoomService roomService;

    @Override
    public boolean add(Hotel hotel) {
        try {
            hotelDao.add(hotel);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        Integer delete = hotelDao.delete(id);
        roomService.deleteHotelRooms(id);
        return delete == 1;
    }

    @Override
    public boolean update(Hotel hotel) {
        return hotelDao.update(hotel) == 1;
    }

    @Override
    public Hotel getById(Integer id) {
        Hotel hotel = hotelDao.getById(id);
        hotel.setRoomList((List<Room>) roomService.getRoomList(id).getData());
        return hotel;
    }

    @Override
    public List<Hotel> getLikeNameAndLikeAddress(String name, String address) {
        return hotelDao.getByLikeNameAndLikeAddress(name, address);
    }
}
