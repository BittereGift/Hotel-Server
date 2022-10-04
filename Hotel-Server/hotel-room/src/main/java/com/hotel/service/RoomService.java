package com.hotel.service;

import com.hotel.domain.Room;

import java.util.List;

/**
 * @author Bittere_Gift
 */
public interface RoomService {
    /**
     * 添加房间
     * @param room 房间对象
     * @return 是否成功
     */
    boolean addRoom(Room room);

    /**
     * 删除房间
     * @param id id
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 删除某个酒店所有的房间
     * @param hotelId 酒店 id
     * @return 是否成功
     */
    boolean deleteByHotel(Integer hotelId);

    /**
     * 更改信息
     * @param room 更改后的对象信息实例
     * @return 是否成功
     */
    boolean update(Room room);

    /**
     * 通过 id 更改房间的状态
     * @param id 房间 id
     * @param status 房间的状态
     * @return 是否成功
     */
    boolean updateStatus(Integer id, Integer status);

    /**
     * 通过 id 查询信息
     * @param id  id
     * @return 查询到的对象信息实例
     */
    Room getById(Integer id);

    /**
     * 查询所有对象的信息
     * @return 所用实例的 List 集合
     */
    List<Room> getAll();

    /**
     * 通过 id 数组获取 room 集合
     *
     * @param ids ids
     * @return rooms
     */
    List<Room> getByIds(List<Integer> ids);

    /**
     * 查询在 hotelId 酒店中 typeId类型的 状态为 status 位置为 position 的房间，不输入条件则为不限制该条件
     *
     * @param hotelId  酒店 id
     * @param typeId   类型 id
     * @param status   状态
     * @param position 房间位置
     * @return 在 hotelId 酒店中 typeId类型的 状态为 status 位置为 position 的房间
     */
    List<Room> getByHotelAndTypeAndStatusAndPosition(Integer hotelId, Integer typeId, Integer status, String position);
}
