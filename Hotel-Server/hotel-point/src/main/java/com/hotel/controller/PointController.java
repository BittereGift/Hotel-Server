package com.hotel.controller;

import com.hotel.service.PointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Bittere_Gift
 */
@RestController
@Slf4j
@RequestMapping("/points")
public class PointController {

    @Autowired
    private PointService pointService;

    /**
     * 查询用户的积分
     *
     * @param userId 用户 id
     * @return 用户积分
     */
    @GetMapping("/{userId}")
    public Result getPointById(@PathVariable Integer userId) {
        Integer point = pointService.getPointById(userId);
        boolean flag = point != null;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        String msg = flag ? "" : "积分获取失败，请重试";
        return new Result(point, code, msg);
    }

    /**
     * 更新用户积分
     *
     * @param userId   用户 id
     * @param newPoint 更新后的用户积分
     * @return 更新成功返回true，否则返回false
     */
    @PutMapping
    public Result updatePoint(@RequestParam("userId") Integer userId, @RequestParam("newPoint") Integer newPoint) {
        boolean flag = pointService.updatePoint(userId, newPoint);
        Integer code = flag? Code.PUT_OK : Code.PUT_FAIL;
        String msg = flag? "" : "积分更新失败，请重试";
        return new Result(flag, code, msg);
    }

    /**
     * 删除用户
     *
     * @param userId 用户 id
     * @return 删除成功返回true，否则返回false
     */
    @DeleteMapping("/{userId}")
    public Result deleteUser(@PathVariable Integer userId) {
        boolean flag = pointService.deleteUser(userId);
        Integer code = flag? Code.DELETE_OK : Code.DELETE_FAIL;
        String msg = flag? "" : "用户删除失败，请重试";
        return new Result(flag, code, msg);
    }

    /**
     * 添加用户，自动设置默认积分为0
     *
     * @param userId 用户id
     * @return 添加是否成功
     */
    @PostMapping("/{userId}")
    public Result addUser(@PathVariable Integer userId) {
        boolean flag = pointService.addUser(userId);
        Integer code = flag? Code.POST_OK : Code.POST_FAIL;
        String msg = flag? "" : "用户添加失败，请重试";
        return new Result(flag, code, msg);
    }

    /**
     * 添加用户，并设置初始积分为提供值
     *
     * @param userId 用户id
     * @param point  初始积分
     * @return 添加是否成功
     */
    @PostMapping
    public Result addUserWithStartPoint(@RequestParam("userId") Integer userId, @RequestParam("point") Integer point) {
        boolean flag = pointService.addUserWithStartPoint(userId, point);
        Integer code = flag? Code.POST_OK : Code.POST_FAIL;
        String msg = flag? "" : "用户添加失败，请重试";
        return new Result(flag, code, msg);
    }
}
