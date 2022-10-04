package com.hotel.service;

import com.hotel.domain.Hotel;

import java.util.List;

/**
 * @author Bittere_Gift
 */
public interface HotelService {

    /**
     * 添加酒店
     * @param hotel 酒店
     * @return 是否成功
     */
    boolean add(Hotel hotel);

    /**
     * 删除酒店
     * @param id 酒店 id
     * @return 是否成功
     */
    boolean delete(Integer id);

    /**
     * 更改酒店信息
     * @param hotel 酒店
     * @return 是否成功
     */
    boolean update(Hotel hotel);

    /**
     * 通过 id 查询酒店
     * @param id id
     * @return 酒店
     */
    Hotel getById(Integer id);

    /**
     * 模糊查询酒店
     * @param name 已经提前处理过的模糊名字
     * @param address 已经提前处理过的模糊位置
     * @return 酒店
     */
    List<Hotel> getLikeNameAndLikeAddress(String name, String address);
}
