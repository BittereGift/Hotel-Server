package com.hotel.controller;

import com.hotel.domain.Manager;
import com.hotel.service.ManagerService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Bittere_Gift
 */
@RestController
@RequestMapping("/managers")
@Slf4j
@Api(tags = "Manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    /**
     * 添加管理员
     *
     * @param manager 管理员
     * @return 是否成功添加
     */
    @ApiOperation(value = "addManager", notes = "添加管理员", response = Boolean.class)
    @ApiResponses({
            @ApiResponse(code = 10001, message = ""),
            @ApiResponse(code = 10000, message = "管理员添加失败，请重试")
    })
    @PostMapping
    public Result addManager(@RequestBody Manager manager) {
        boolean flag = managerService.add(manager);
        Integer code = flag ? Code.POST_OK : Code.POST_FAIL;
        String msg = flag ? "" : "管理员添加失败，请重试";
        return new Result(flag, code, msg);
    }

    /**
     * 删除管理员
     *
     * @param id id
     * @return 是否成功
     */
    @ApiOperation(value = "deleteManager", notes = "删除管理员", response = Boolean.class)
    @ApiResponses({
            @ApiResponse(code = 10021, message = ""),
            @ApiResponse(code = 10020, message = "管理员删除失败，请重试")
    })
    @DeleteMapping("/{id}")
    public Result deleteManager(@PathVariable Integer id) {
        boolean flag = managerService.deleteById(id);
        Integer code = flag ? Code.DELETE_OK : Code.DELETE_FAIL;
        String msg = flag ? "" : "管理员删除失败，请重试";
        return new Result(flag, code, msg);
    }

    /**
     * 更新管理员
     *
     * @param manager 管理员
     * @return 是否成功
     */
    @ApiOperation(value = "updateManager", notes = "更新管理员", response = Boolean.class)
    @ApiResponses({
            @ApiResponse(code = 10031, message = ""),
            @ApiResponse(code = 10030, message = "管理员更新失败，请重试")
    })
    @PutMapping
    public Result updateManager(@RequestBody Manager manager) {
        boolean flag = managerService.update(manager);
        Integer code = flag ? Code.PUT_OK : Code.PUT_FAIL;
        String msg = flag ? "" : "管理员更新失败，请重试";
        return new Result(flag, code, msg);
    }

    /**
     * 查询管理员
     *
     * @param id id
     * @return 管理员
     */
    @ApiOperation(value = "getManagerById", notes = "查询管理员", response = Manager.class)
    @ApiResponses({
            @ApiResponse(code = 10011, message = ""),
            @ApiResponse(code = 10010, message = "管理员查询失败，请重试")
    })
    @GetMapping("/{id}")
    public Result getManagerById(@PathVariable Integer id) {
        Manager manager = managerService.getById(id);
        boolean flag = manager != null;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        String msg = flag ? "" : "管理员查询失败，请重试";
        return new Result(manager, code, msg);
    }

    /**
     * 查询管理员
     *
     * @return List of manager
     */
    @ApiOperation(value = "getAllManagers", notes = "查询管理员", response = Manager.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 10011, message = ""),
            @ApiResponse(code = 10010, message = "管理员查询失败，请重试")
    })
    @GetMapping
    public Result getAllManagers() {
        List<Manager> managers = managerService.getAll();
        boolean flag = managers != null;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        String msg = flag ? "" : "管理员查询失败，请重试";
        return new Result(managers, code, msg);
    }

    /**
     * 登录验证
     *
     * @param name     用户名
     * @param password 密码
     * @return 登陆对象
     */
    @ApiOperation(value = "loginCheck", notes = "登录验证", response = Manager.class)
    @ApiResponses({
            @ApiResponse(code = 10011, message = ""),
            @ApiResponse(code = 10010, message = "登录失败，请重试")
    })
    @GetMapping("/l")
    public Result loginCheck(@RequestParam String name, @RequestParam String password) {
        Manager manager = managerService.loginCheck(name, password);
        boolean flag = manager != null;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        String msg = flag ? "" : "登录失败，请重试";
        return new Result(manager, code, msg);
    }
}
