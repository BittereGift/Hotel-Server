package com.hotel.service;

import com.hotel.domain.Manager;

import java.util.List;

/**
 * @author Bittere_Gift
 */
public interface ManagerService {

    /**
     * 添加管理员
     * @param manager 管理员
     * @return 是否成功添加
     */
    boolean add(Manager manager);

    /**
     * 删除管理员
     * @param id id
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 更新管理员
     * @param manager 管理员
     * @return 是否成功
     */
    boolean update(Manager manager);

    /**
     * 查询管理员
     * @param id id
     * @return 管理员
     */
    Manager getById(Integer id);

    /**
     * 查询管理员
     * @return List of manager
     */
    List<Manager> getAll();

    /**
     * 登录验证
     * @param name 用户名
     * @param password 密码
     * @return 登陆对象
     */
    Manager loginCheck(String name, String password);
}
