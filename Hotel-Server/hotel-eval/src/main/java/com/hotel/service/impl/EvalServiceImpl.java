package com.hotel.service.impl;

import com.hotel.dao.EvalDao;
import com.hotel.domain.Evaluation;
import com.hotel.service.EvalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Bittere_Gift
 */
@Service
public class EvalServiceImpl implements EvalService {

    @Autowired
    private EvalDao evalDao;

    @Override
    public boolean add(Evaluation eval) {
        try {
            evalDao.add(eval);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Integer orderId) {
        return evalDao.delete(orderId) == 1;
    }

    @Override
    public boolean update(Evaluation eval) {
        return evalDao.update(eval) == 1;
    }

    @Override
    public Evaluation getById(Integer orderId) {
        return evalDao.getById(orderId);
    }

    @Override
    public List<Evaluation> getAll() {
        return evalDao.getAll();
    }

    @Override
    public List<Evaluation> getByRoom(Integer roomId) {
        return evalDao.getByRoom(roomId);
    }

    @Override
    public Float getAverageScoreOfRoom(Integer roomId) {
        return evalDao.getAverageScoreOfRoom(roomId);
    }

    @Override
    public List<Evaluation> getScoreLowerThanGivenOfRoom(Integer roomId, Integer givenPoint) {
        return evalDao.getScoreLowerThanGivenOfRoom(roomId, givenPoint);
    }

    @Override
    public List<Evaluation> getScoreHigherThanGivenOfRoom(Integer roomId, Integer givenPoint) {
        return evalDao.getScoreHigherThanGivenOfRoom(roomId, givenPoint);
    }

    @Override
    public List<Evaluation> getByHotel(Integer hotelId) {
        return evalDao.getByHotel(hotelId);
    }

    @Override
    public Float getAverageScoreOfHotel(Integer hotelId) {
        return evalDao.getAverageScoreOfHotel(hotelId);
    }

    @Override
    public List<Evaluation> getScoreLowerThanGivenOfHotel(Integer hotelId, Integer givenPoint) {
        return evalDao.getScoreLowerThanGivenOfHotel(hotelId, givenPoint);
    }

    @Override
    public List<Evaluation> getScoreHigherThanGivenOfHotel(Integer hotelId, Integer givenPoint) {
        return evalDao.getScoreHigherThanGivenOfHotel(hotelId, givenPoint);
    }
}
