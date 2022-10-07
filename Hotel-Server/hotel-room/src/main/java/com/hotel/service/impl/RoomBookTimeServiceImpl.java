package com.hotel.service.impl;

import com.hotel.dao.RoomBookTimeDao;
import com.hotel.domain.BookTime;
import com.hotel.exception.BusinessException;
import com.hotel.service.RoomBookTimeService;
import com.hotel.util.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.hotel.exception.ExceptionEnum.ROOM_ALREADY_BOOKED;

/**
 * @author Bittere_Gift
 */
@Service
@Slf4j
public class RoomBookTimeServiceImpl implements RoomBookTimeService {

    @Autowired
    private RoomBookTimeDao roomBookTimeDao;

    @Override
    public boolean add(BookTime bookTime) {
        List<BookTime> bookTimeList = roomBookTimeDao.getByRoomId(bookTime.getRoomId());
        if (DataUtil.isBookTimeValid(bookTime, bookTimeList)) {
            log.info("bookTime: {}", bookTime);
            roomBookTimeDao.add(bookTime);
            return true;
        }
        throw new BusinessException(ROOM_ALREADY_BOOKED);
    }

    @Override
    public boolean delete(Integer orderId) {
        return roomBookTimeDao.delete(orderId) == 1;
    }

    @Override
    public boolean update(BookTime bookTime) {
        return roomBookTimeDao.update(bookTime) == 1;
    }

    @Override
    public BookTime getByOrderId(Integer orderId) {
        return roomBookTimeDao.getByOrderId(orderId);
    }

    @Override
    public List<BookTime> getByRoomId(Integer roomId) {
        return roomBookTimeDao.getByRoomId(roomId);
    }
}
