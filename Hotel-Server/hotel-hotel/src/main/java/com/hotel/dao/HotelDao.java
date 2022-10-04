package com.hotel.dao;

import com.hotel.domain.Hotel;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

/**
 * @author Bittere_Gift
 */
@Mapper
public interface HotelDao {

    /**
     * 添加酒店
     * @param hotel 酒店
     */
    @Insert("insert into hotel_hotel values (null, #{name}, #{address}, #{description})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void add(Hotel hotel);

    /**
     * 删除酒店
     * @param id 酒店 id
     * @return 删除的条数
     */
    @Delete("delete from hotel_hotel where id = #{id}")
    Integer delete(Integer id);

    /**
     * 更改酒店信息
     * @param hotel 酒店
     * @return 更新条数
     */
    @Update("update hotel_hotel set name = #{name}, address = #{address}, description = #{description} where id = #{id}")
    Integer update(Hotel hotel);

    /**
     * 通过 id 查询酒店
     * @param id id
     * @return 酒店
     */
    @Select("select * from hotel_hotel where id = #{id}")
    Hotel getById(Integer id);

    /**
     * 模糊查询名字和地址，可选，如果没有输入任何参数，就是查询所有
     * @param likeName 模糊名字
     * @param likeAddress 模糊地址
     * @return 酒店
     */
    @SelectProvider(value = HotelSqlProvider.class, method = "get")
    List<Hotel> getByLikeNameAndLikeAddress(@Param("likeName") String likeName, @Param("likeAddress") String likeAddress);

    class HotelSqlProvider {
        public String get(Map<String, Object> param) {
            return new SQL(){{
                SELECT("*");
                FROM("hotel_hotel");
                if (param.get("likeName") != null) {
                    WHERE("name like #{likeName}");
                }
                if (param.get("likeAddress")!= null) {
                    WHERE("address like #{likeAddress}");
                }
            }}.toString();
        }
    }
}
