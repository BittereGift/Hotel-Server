package com.hotel.service.impl;

import com.hotel.dao.UserDao;
import com.hotel.domain.User;
import com.hotel.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Bittere_Gift
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean addUser(User user) {
        log.info("User adding: {}", user);
        try {
            userDao.add(user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteUserById(Integer id) {
        log.info("User deleting: id = {}", id);
        Integer code = userDao.deleteById(id);
        return code == 1;
    }

    @Override
    public boolean update(User user) {
        log.info("User updating: {}", user);
        Integer code = userDao.update(user);
        return code == 1;
    }

    @Override
    public User getById(Integer id) {
        log.info("User getting: id = {}", id);
        return userDao.getById(id);
    }

    @Override
    public List<User> getAll() {
        log.info("User getting all");
        return userDao.getAll();
    }

    @Override
    public List<User> getByIds(List<Integer> ids) {
        return userDao.getByIds(ids);
    }

    @Override
    public Integer hasSameNameUser(String name) {
        log.info("User hasSameNameUser: name = {}", name);
        return userDao.countSameNameUser(name);
    }

    @Override
    public Integer hasSameEmailUser(String email) {
        log.info("User hasSameEmailUser: email = {}", email);
        return userDao.countSameEmailUser(email);
    }

    @Override
    public Integer hasSameTeleUser(String tele) {
        log.info("User hasSameTeleUser: tele = {}", tele);
        return userDao.countSameTeleUser(tele);
    }

    @Override
    public Integer hasSameQQUser(String qq) {
        log.info("User hasSameQQUser: qq = {}", qq);
        return userDao.countSameQQUser(qq);
    }

    @Override
    public User loginUser(String info, String password) {
        log.info("User loginUser: info = {}, password = {}", info, password);
        return userDao.loginUser(info, password);
    }
}
