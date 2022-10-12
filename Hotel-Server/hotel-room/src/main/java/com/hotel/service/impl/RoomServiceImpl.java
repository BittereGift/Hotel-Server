package com.hotel.service.impl;

import com.hotel.controller.Result;
import com.hotel.dao.RoomDao;
import com.hotel.domain.Hotel;
import com.hotel.domain.Room;
import com.hotel.service.HotelService;
import com.hotel.service.RoomService;
import com.hotel.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Bittere_Gift
 */
@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private HotelService hotelService;

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
        Result result = hotelService.getHotelById(room.getHotel().getId());
        Hotel hotel = DataUtil.getDataFromFeign(result.getData(), Hotel.class);
        room.setHotel(hotel);
        return room;
    }

    @Override
    public List<Room> getAll() {
        List<Room> all = roomDao.getAll();
        insertHotelInfos(all);
        return all;
    }

    @Override
    public List<Room> getByIds(List<Integer> ids) {
        List<Room> all = roomDao.getByIds(ids);
        insertHotelInfos(all);
        return all;
    }

    @Override
    public List<Room> getByHotelAndTypeAndStatusAndPosition(Integer hotelId, Integer typeId, String position) {
        return roomDao.getByHotelAndTypeAndStatusAndPosition(hotelId, typeId, position);
    }

    /**
     * 完善房间的酒店信息
     * @param rooms 房间列表
     */
    private void insertHotelInfos(List<Room> rooms) {
        Map<Integer, Hotel> fundedHotelMap = new HashMap<>();
        for (Room room : rooms) {
            Integer hotelId = room.getHotel().getId();
            if (fundedHotelMap.containsKey(hotelId)) {
                room.setHotel(fundedHotelMap.get(hotelId));
            } else {
                Result result = hotelService.getHotelById(hotelId);
                Hotel hotel = DataUtil.getDataFromFeign(result.getData(), Hotel.class);
                room.setHotel(hotel);
                fundedHotelMap.put(hotelId, hotel);
            }
        }
    }

}
