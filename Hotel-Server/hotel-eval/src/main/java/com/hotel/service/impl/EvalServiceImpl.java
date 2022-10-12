package com.hotel.service.impl;

import com.hotel.controller.Result;
import com.hotel.dao.EvalDao;
import com.hotel.domain.Evaluation;
import com.hotel.domain.Order;
import com.hotel.service.EvalService;
import com.hotel.service.OrderService;
import com.hotel.util.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Bittere_Gift
 */
@Service
@Slf4j
public class EvalServiceImpl implements EvalService {

    @Autowired
    private EvalDao evalDao;

    @Autowired
    private OrderService orderService;

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
        return insertInfos(evalDao.getById(orderId));
    }

    @Override
    public List<Evaluation> getAll() {
        List<Evaluation> all = evalDao.getAll();
        all.forEach(this::insertInfos);
        return all;
    }

    @Override
    public List<Evaluation> getByRoom(Integer roomId) {
        List<Evaluation> all = evalDao.getByRoom(roomId);
        all.forEach(this::insertInfos);
        return all;
    }

    @Override
    public Float getAverageScoreOfRoom(Integer roomId) {
        return evalDao.getAverageScoreOfRoom(roomId);
    }

    @Override
    public List<Evaluation> getScoreLowerThanGivenOfRoom(Integer roomId, Integer givenPoint) {
        List<Evaluation> all = evalDao.getScoreLowerThanGivenOfRoom(roomId, givenPoint);
        all.forEach(this::insertInfos);
        return all;
    }

    @Override
    public List<Evaluation> getScoreHigherThanGivenOfRoom(Integer roomId, Integer givenPoint) {
        List<Evaluation> all = evalDao.getScoreHigherThanGivenOfRoom(roomId, givenPoint);
        all.forEach(this::insertInfos);
        return all;
    }

    @Override
    public List<Evaluation> getByHotel(Integer hotelId) {
        List<Evaluation> all = evalDao.getByHotel(hotelId);
        all.forEach(this::insertInfos);
        return all;
    }

    @Override
    public Float getAverageScoreOfHotel(Integer hotelId) {
        return evalDao.getAverageScoreOfHotel(hotelId);
    }

    @Override
    public List<Evaluation> getScoreLowerThanGivenOfHotel(Integer hotelId, Integer givenPoint) {
        List<Evaluation> all = evalDao.getScoreLowerThanGivenOfHotel(hotelId, givenPoint);
        all.forEach(this::insertInfos);
        return all;
    }

    @Override
    public List<Evaluation> getScoreHigherThanGivenOfHotel(Integer hotelId, Integer givenPoint) {
        List<Evaluation> all = evalDao.getScoreHigherThanGivenOfHotel(hotelId, givenPoint);
        all.forEach(this::insertInfos);
        return all;
    }

    /**
     * 补全信息
     * @param originEval 未补全的信息
     * @return 补全了之后的评论对象
     */
    private Evaluation insertInfos(Evaluation originEval) {
        Result result = orderService.getOrderById(originEval.getOrder().getOrderId());
        Order data = DataUtil.getDataFromFeign(result.getData(), Order.class);
        originEval.setOrder(data);
        return originEval;
    }
}
