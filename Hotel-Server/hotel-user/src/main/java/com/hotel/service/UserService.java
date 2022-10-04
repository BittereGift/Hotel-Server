package com.hotel.service;

import com.hotel.domain.User;

import java.util.List;

/**
 * @author Bittere_Gift
 */
public interface UserService {

    /**
     * 通过 User 对象添加用户
     * @param user 用户对象
     */
    boolean addUser(User user);

    /**
     * 通过用户 id 删除用户，如果用户不存在，则返回 false
     * @param id 用户 id
     * @return 如果用户不存在，则返回 false，否则返回 true
     */
    boolean deleteUserById(Integer id);

    /**
     * 更改用户信息
     * @param user 更改后的用户信息实例
     * @return 如果更改成功，则返回 true，否则返回 false
     */
    boolean update(User user);

    /**
     * 通过用户 id 查询用户信息
     * @param id 用户 id
     * @return 查询到的用户信息实例
     */
    User getById(Integer id);

    /**
     * 查询所有用户的信息
     * @return 所用用户实例的 List 集合
     */
    List<User> getAll();

    /**
     * 通过 ids 获取用户列表
     * @param ids 用户 id
     * @return 用户列表
     */
    List<User> getByIds(List<Integer> ids);

    /**
     * 判断是否存在和提供的 name 相同的已存在用户
     * @param name 提供的 name
     * @return 如果存在，返回 true，否则返回 false
     */
    Integer hasSameNameUser(String name);

    /**
     * 判断是否存在和提供的 email 相同的已存在用户
     * @param email 提供的 email
     * @return 如果存在，返回 true，否则返回 false
     */
    Integer hasSameEmailUser(String email);

    /**
     * 判断是否存在和提供的 tele 相同的已存在用户
     * @param tele 提供的 tele
     * @return 如果存在，返回 true，否则返回 false
     */
    Integer hasSameTeleUser(String tele);

    /**
     * 判断是否存在和提供的 qq 相同的已存在用户
     * @param qq 提供的 qq
     * @return 如果存在，返回 true，否则返回 false
     */
    Integer hasSameQQUser(String qq);

    /**
     * 通过用户提供的信息查询当前用户是否存在，即登录
     * @param info 用户提供的字段信息，可以是用户名，手机号码，或者邮箱
     * @param password 密码
     * @return 登录的用户实例
     */
    User loginUser(String info, String password);
}
