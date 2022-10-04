package com.hotel.service.impl;

import com.hotel.dao.LogDao;
import com.hotel.domain.Status;
import com.hotel.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Bittere_Gift
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;

    @Override
    public boolean add(Integer orderId, String info) {
        try {
            logDao.add(orderId, info);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<Status> getLogsByOrder(Integer orderId) {
        return logDao.getLogsByOrder(orderId);
    }
}
