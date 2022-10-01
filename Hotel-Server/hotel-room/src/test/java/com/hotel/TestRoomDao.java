package com.hotel;

import com.hotel.dao.RoomDao;
import com.hotel.domain.Room;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = RoomApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class TestRoomDao {

    @Autowired
    private RoomDao roomDao;

    @Test
    public void testGetById() {
        Room room = roomDao.getById(1);
        log.info("get {}", room);
    }
}
