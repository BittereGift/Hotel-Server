package com.hotel.service;

import com.hotel.domain.Evaluation;

import java.util.List;

/**
 * @author Bittere_Gift
 */
public interface EvalService {

    /**
     * 新增评论
     *
     * @param eval 评论
     * @return 是否成功
     */
    boolean add(Evaluation eval);

    /**
     * 删除评论
     *
     * @param orderId 订单 id
     * @return 是否成功
     */
    boolean delete(Integer orderId);

    /**
     * 更新评论
     *
     * @param eval 新评论
     * @return 是否成功
     */
    boolean update(Evaluation eval);

    /**
     * 通过订单 id 查询评论
     *
     * @param orderId 订单 id
     * @return 评论
     */
    Evaluation getById(Integer orderId);

    /**
     * 查询所有评论
     *
     * @return 所有评论
     */
    List<Evaluation> getAll();

    /**
     * 通过房间号查询评论
     *
     * @param roomId 房间号
     * @return 评论
     */
    List<Evaluation> getByRoom(Integer roomId);

    /**
     * 获取某个房间的平均分
     *
     * @param roomId 房价 id
     * @return 平均分
     */
    Float getAverageScoreOfRoom(Integer roomId);

    /**
     * 查询某个房间评分低于指定分数的评论
     *
     * @param roomId 房间号
     * @return 评论
     */
    List<Evaluation> getScoreLowerThanGivenOfRoom(Integer roomId, Integer givenPoint);

    /**
     * 查询某个房间评分高于指定分数的评论
     *
     * @param roomId 房间号
     * @return 评论
     */
    List<Evaluation> getScoreHigherThanGivenOfRoom(Integer roomId, Integer givenPoint);

    /**
     * 通过酒店查询评论
     *
     * @param hotelId 酒店
     * @return 评论
     */
    List<Evaluation> getByHotel(Integer hotelId);


    /**
     * 获取某个酒店的平均分
     *
     * @param hotelId 酒店 id
     * @return 平均分
     */
    Float getAverageScoreOfHotel(Integer hotelId);

    /**
     * 查询某个酒店评分低于指定分数的评论
     *
     * @param hotelId 酒店号
     * @return 评论
     */
    List<Evaluation> getScoreLowerThanGivenOfHotel(Integer hotelId, Integer givenPoint);

    /**
     * 查询某个酒店评分高于指定分数的评论
     *
     * @param hotelId 酒店号
     * @return 评论
     */
    List<Evaluation> getScoreHigherThanGivenOfHotel(Integer hotelId, Integer givenPoint);


}
