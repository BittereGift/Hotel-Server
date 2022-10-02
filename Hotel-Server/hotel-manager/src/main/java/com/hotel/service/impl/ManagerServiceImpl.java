package com.hotel.service.impl;

import com.hotel.dao.ManagerDao;
import com.hotel.domain.Manager;
import com.hotel.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Bittere_Gift
 */
@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerDao managerDao;

    @Override
    public boolean add(Manager manager) {
        try {
            managerDao.add(manager);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteById(Integer id) {
        return managerDao.deleteById(id) == 1;
    }

    @Override
    public boolean update(Manager manager) {
        return managerDao.update(manager) == 1;
    }

    @Override
    public Manager getById(Integer id) {
        return managerDao.getById(id);
    }

    @Override
    public List<Manager> getAll() {
        return managerDao.getAll();
    }

    @Override
    public Manager loginCheck(String name, String password) {
        return managerDao.loginCheck(name, password);
    }
}
