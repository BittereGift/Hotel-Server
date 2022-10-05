package com.hotel.service.overwrite;

import com.hotel.domain.Hotel;
import com.hotel.service.HotelService;
import com.hotel.util.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Bittere_Gift
 */
@Service
@Slf4j
public class HotelServiceOverwrite {

    @Autowired
    private HotelService hotelService;

    public Hotel getHotelById(Integer id) {
        Hotel hotel = DataUtil.getDataFromFeign(hotelService.getHotelById(id).getData(), Hotel.class);
        hotel.setRoomList(null);
        log.info("{}", hotel);
        return hotel;
    }
}
