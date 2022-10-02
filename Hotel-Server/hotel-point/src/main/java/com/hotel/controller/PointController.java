package com.hotel.controller;

import com.hotel.domain.User;
import com.hotel.service.PointService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Bittere_Gift
 */
@RestController
@Slf4j
@RequestMapping("/points")
@Api(tags = "Point")
public class PointController {

    @Autowired
    private PointService pointService;

    /**
     * 查询用户的积分
     *
     * @param userId 用户 id
     * @return 用户积分
     */
    @ApiOperation(value = "getPointById", notes = "查询用户积分")
    @ApiImplicitParam(name = "id", required = true, paramType = "path")
    @ApiResponses({
            @ApiResponse(code = 10011, message = "", response = Integer.class),
            @ApiResponse(code = 10010, message = "积分查询失败，请重试")
    })
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
    @ApiOperation(value = "updatePoint", notes = "更改积分")
    @ApiResponses({
            @ApiResponse(code = 10031, message = "", response = Boolean.class),
            @ApiResponse(code = 10030, message = "更新失败，请重试", response = Boolean.class)
    })
    @PutMapping
    public Result updatePoint(@RequestParam("userId") Integer userId,
                              @RequestParam("newPoint") Integer newPoint) {
        boolean flag = pointService.updatePoint(userId, newPoint);
        Integer code = flag ? Code.PUT_OK : Code.PUT_FAIL;
        String msg = flag ? "" : "积分更新失败，请重试";
        return new Result(flag, code, msg);
    }

    /**
     * 删除用户
     *
     * @param userId 用户 id
     * @return 删除成功返回true，否则返回false
     */
    @ApiOperation(value = "deleteUser", notes = "删除用户")
    @ApiResponses({
            @ApiResponse(code = 10021, message = "", response = Boolean.class),
            @ApiResponse(code = 10020, message = "删除失败，请重试", response = Boolean.class)
    })
    @DeleteMapping("/{userId}")
    public Result deleteUser(@PathVariable Integer userId) {
        boolean flag = pointService.deleteUser(userId);
        Integer code = flag ? Code.DELETE_OK : Code.DELETE_FAIL;
        String msg = flag ? "" : "用户删除失败，请重试";
        return new Result(flag, code, msg);
    }

    /**
     * 添加用户，并设置初始积分为提供值，如果不设置值，默认为0
     *
     * @param userId 用户id
     * @param point  初始积分
     * @return 添加是否成功
     */
    @ApiOperation(value = "addUserWithStartPoint", notes = "添加用户，并设置初始积分，如果不设置值，默认为0")
    @ApiResponses({
            @ApiResponse(code = 10001, message = "", response = Boolean.class),
            @ApiResponse(code = 10000, message = "新建失败，请重试", response = Boolean.class)
    })
    @PostMapping
    public Result addUserWithStartPoint(@RequestParam("userId") Integer userId,
                                        @RequestParam(value = "point", required = false) Integer point) {
        boolean flag = pointService.addUserWithStartPoint(userId, point);
        Integer code = flag ? Code.POST_OK : Code.POST_FAIL;
        String msg = flag ? "" : "用户添加失败，请重试";
        return new Result(flag, code, msg);
    }
}
