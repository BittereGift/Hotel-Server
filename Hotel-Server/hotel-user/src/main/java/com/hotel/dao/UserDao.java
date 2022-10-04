package com.hotel.dao;

import com.hotel.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bittere_Gift
 */
@Mapper
public interface UserDao {

    /**
     * 通过 User 对象添加用户
     *
     * @param user 用户对象
     */
    @Insert("insert into user_user values (null, #{name}, #{password}, #{email}, #{tele}, #{qq}, #{sex})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void add(User user);

    /**
     * 通过用户 id 删除用户，如果用户不存在，则返回 0
     *
     * @param id 用户 id
     * @return 如果用户不存在，则返回 0，否则返回 1
     */
    @Delete("delete from user_user where id = #{id}")
    Integer deleteById(Integer id);

    /**
     * 更改用户信息
     *
     * @param user 更改后的用户信息实例
     * @return 如果更改成功，则返回 1，否则返回 0
     */
    @Update("update user_user set name = #{name}, password = #{password}, email = #{email}, tele = #{tele}, " +
            "qq = #{qq}, sex = #{sex} where id = #{id}")
    Integer update(User user);

    /**
     * 通过用户 id 查询用户信息
     *
     * @param id 用户 id
     * @return 查询到的用户信息实例
     */
    @Select("select * from user_user where id = #{id}")
    User getById(Integer id);

    /**
     * 查询所有用户的信息
     *
     * @return 所用用户实例的 List 集合
     */
    @Select("select * from user_user")
    List<User> getAll();

    /**
     * 通过 ids 获取用户列表
     * @param ids 用户 id
     * @return 用户列表
     */
    @SelectProvider(value = UserSqlProvider.class, method = "selectUsersByIds")
    List<User> getByIds(@Param("ids") List<Integer> ids);

    /**
     * 查询和提供的 name 相同的已存在用户的数量
     *
     * @param name 提供的 name
     * @return 和提供的 name 相同的已存在用户的数量
     */
    @Select("select COUNT(*) from user_user where name = #{name}")
    Integer countSameNameUser(String name);

    /**
     * 查询和提供的 email 相同的已存在用户的数量
     *
     * @param email 提供的 email
     * @return 和提供的 email 相同的已存在用户的数量
     */
    @Select("select COUNT(*) from user_user where email = #{email}")
    Integer countSameEmailUser(String email);

    /**
     * 查询和提供的 tele 相同的已存在用户的数量
     *
     * @param tele 提供的 tele
     * @return 和提供的 tele 相同的已存在用户的数量
     */
    @Select("select COUNT(*) from user_user where tele = #{tele}")
    Integer countSameTeleUser(String tele);

    /**
     * 查询和提供的 qq 相同的已存在用户的数量
     *
     * @param qq 提供的 qq
     * @return 和提供的 qq 相同的已存在用户的数量
     */
    @Select("select COUNT(*) from user_user where qq = #{qq}")
    Integer countSameQQUser(String qq);

    /**
     * 通过用户提供的信息查询当前用户是否存在，即登录
     *
     * @param info     用户提供的字段信息，可以是用户名，手机号码，或者邮箱
     * @param password 密码
     * @return 登录的用户实例
     */
    @Select("select * from user_user where password = #{password} and (name = #{info} or email = #{info} or tele = #{info})")
    User loginUser(String info, String password);

    class UserSqlProvider {
        public String selectUsersByIds(List<Integer> ids) {
            return new SQL() {{
                List<String> list = new ArrayList<>();
                ids.forEach(id -> list.add(id.toString()));
                SELECT("*");
                FROM("user_user");
                WHERE("id in (" + String.join(",", list) + ")");
            }}.toString();
        }
    }
}
