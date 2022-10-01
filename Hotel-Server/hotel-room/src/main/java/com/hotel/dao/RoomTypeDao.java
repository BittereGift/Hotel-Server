package com.hotel.dao;

import com.hotel.domain.Room;
import com.hotel.domain.RoomType;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoomTypeDao {

    /**
     * 添加
     * @param type 对象
     */
    @Insert("insert into room_type values (null, #{name}, #{price})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void add(RoomType type);

    /**
     * 删除
     * @param id id
     * @return 如果不存在，则返回 0，否则返回 1
     */
    @Delete("delete from room_type where id = #{id}")
    Integer deleteById(Integer id);

    /**
     * 更改信息
     * @param type 更改后的对象信息实例
     * @return 如果更改成功，则返回 1，否则返回 0
     */
    @Update("update room_type set name = #{name}, price = #{price} where id = #{id}")
    Integer update(RoomType type);

    /**
     * 通过 id 查询
     * @param id id
     * @return 查询的结果对象
     */
    @Select("select * from room_type where id = #{id}")
    RoomType getById(Integer id);

    /**
     * 查询所有房间类型
     * @return 房间类型
     */
    @Select("select * from room_type")
    List<RoomType> getAll();
}
