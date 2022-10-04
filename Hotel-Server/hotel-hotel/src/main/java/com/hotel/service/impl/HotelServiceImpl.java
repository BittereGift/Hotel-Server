package com.hotel.service.impl;

import com.hotel.dao.HotelDao;
import com.hotel.domain.Hotel;
import com.hotel.service.HotelService;
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
        return hotelDao.delete(id) == 1;
    }

    @Override
    public boolean update(Hotel hotel) {
        return hotelDao.update(hotel) == 1;
    }

    @Override
    public Hotel getById(Integer id) {
        return hotelDao.getById(id);
    }

    @Override
    public List<Hotel> getLikeNameAndLikeAddress(String name, String address) {
        return hotelDao.getByLikeNameAndLikeAddress(name, address);
    }
}
