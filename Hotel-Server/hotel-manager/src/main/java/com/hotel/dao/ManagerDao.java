package com.hotel.dao;

import com.hotel.domain.Manager;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Bittere_Gift
 */
@Mapper
public interface ManagerDao {

    /**
     * 添加管理员
     * @param manager 管理员
     */
    @Insert("insert into manager_manager values (null, #{name}, #{password}, #{qq}," +
            " #{hotel.id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void add(Manager manager);

    /**
     * 删除管理员
     * @param id id
     * @return 删除的行数
     */
    @Delete("delete from manager_manager where id = #{id}")
    Integer deleteById(Integer id);

    /**
     * 更新管理员
     * @param manager 管理员
     * @return 更新条数
     */
    @Update("update manager_manager set name = #{name}, password = #{password}, qq = #{qq}, " +
            "hotel_id = #{hotel.id} where id = #{id}")
    Integer update(Manager manager);

    /**
     * 查询管理员
     * @param id id
     * @return 管理员
     */
    @Select("select * from manager_manager where id = #{id}")
    @Results(id = "managerMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "password", column = "password"),
            @Result(property = "qq", column = "qq"),
            @Result(property = "hotel.id", column = "hotel_id")
    })
    Manager getById(Integer id);

    /**
     * 查询管理员
     * @return List of manager
     */
    @Select("select * from manager_manager")
    @ResultMap("managerMap")
    List<Manager> getAll();

    /**
     * 登录验证
     * @param name 用户名
     * @param password 密码
     * @return 登陆对象
     */
    @Select("select * from manager_manager where name = #{name} and password = #{password}")
    @ResultMap("managerMap")
    Manager loginCheck(String name, String password);
}
