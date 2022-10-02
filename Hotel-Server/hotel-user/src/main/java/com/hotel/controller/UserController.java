package com.hotel.controller;


import com.hotel.domain.User;
import com.hotel.service.UserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Bittere_Gift
 */
@RestController
@RequestMapping("/users")
@Slf4j
@Api(tags = "User")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "addUser", notes = "添加用户")
    @ApiImplicitParam(name = "user", required = true, paramType = "body")
    @ApiResponses({
            @ApiResponse(code = 10001, message = "", response = Boolean.class),
            @ApiResponse(code = 10000, message = "新建失败，请重试", response = Boolean.class)
    })
    @PostMapping
    public Result addUser(@RequestBody User user) {
        boolean flag = userService.addUser(user);
        Integer code = flag ? Code.POST_OK : Code.POST_FAIL;
        String msg = flag ? "" : "用户新建失败，请重试";
        return new Result(flag, code, msg);
    }

    @ApiOperation(value = "deleteUserById", notes = "删除用户")
    @ApiImplicitParam(name = "id", required = true, paramType = "path")
    @ApiResponses({
            @ApiResponse(code = 10021, message = "", response = Boolean.class),
            @ApiResponse(code = 10020, message = "删除失败，请重试", response = Boolean.class)
    })
    @DeleteMapping("/{id}")
    public Result deleteUserById(@PathVariable("id") Integer id) {
        boolean flag = userService.deleteUserById(id);
        Integer code = flag ? Code.DELETE_OK : Code.DELETE_FAIL;
        String msg = flag ? "" : "用户删除失败，请重试";
        return new Result(flag, code, msg);
    }

    @ApiOperation(value = "updateUser", notes = "更改用户信息")
    @ApiImplicitParam(name = "user", required = true, paramType = "body")
    @ApiResponses({
            @ApiResponse(code = 10031, message = "", response = Boolean.class),
            @ApiResponse(code = 10030, message = "更新失败，请重试", response = Boolean.class)
    })
    @PutMapping
    public Result updateUser(@RequestBody User user) {
        boolean flag = userService.update(user);
        Integer code = flag ? Code.PUT_OK : Code.PUT_FAIL;
        String msg = flag ? "" : "用户更新失败，请重试";
        return new Result(flag, code, msg);
    }

    @ApiOperation(value = "getUserById", notes = "查询用户信息")
    @ApiImplicitParam(name = "id", required = true, paramType = "path")
    @ApiResponses({
            @ApiResponse(code = 10011, message = "", response = User.class),
            @ApiResponse(code = 10010, message = "用户查询失败，请重试")
    })
    @GetMapping("/{id}")
    public Result getUserById(@PathVariable("id") Integer id) {
        User user = userService.getById(id);
        boolean flag = user != null;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        String msg = flag ? "" : "用户查询失败，请重试";
        return new Result(user, code, msg);
    }

    @ApiOperation(value = "getAllUsers", notes = "查询所有用户的信息")
    @ApiResponses({
            @ApiResponse(code = 10011, message = "", response = List.class),
            @ApiResponse(code = 10010, message = "用户查询失败，请重试")
    })
    @GetMapping
    public Result getAllUsers() {
        List<User> userList = userService.getAll();
        boolean flag = userList != null;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        String msg = flag ? "" : "用户查询失败，请重试";
        return new Result(userList, code, msg);
    }

    /**
     * 验证 name 字段是否重复，如果重复，返回false
     *
     * @param name 验证字段
     * @return 如果重复，返回false
     */
    @ApiOperation(value = "nameCheck", notes = "验证 name 字段是否重复")
    @ApiResponses({
            @ApiResponse(code = 10011, message = "", response = Boolean.class),
            @ApiResponse(code = 10010, message = "用户名已被注册", response = Boolean.class)
    })
    @GetMapping("/name_check")
    public Result nameCheck(@RequestParam("name") String name) {
        Integer count = userService.hasSameNameUser(name);
        boolean flag = count == 0;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        String msg = flag ? "" : "用户名已被注册";
        return new Result(flag, code, msg);
    }

    /**
     * 验证 email 字段是否重复，如果重复，返回false
     *
     * @param email 验证字段
     * @return 如果重复，返回false
     */
    @ApiOperation(value = "emailCheck", notes = "验证 email 字段是否重复")
    @ApiResponses({
            @ApiResponse(code = 10011, message = "", response = Boolean.class),
            @ApiResponse(code = 10010, message = "邮箱已被注册", response = Boolean.class)
    })
    @GetMapping("/email_check")
    public Result emailCheck(@RequestParam("email") String email) {
        Integer count = userService.hasSameEmailUser(email);
        boolean flag = count == 0;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        String msg = flag ? "" : "邮箱已被注册";
        return new Result(flag, code, msg);
    }

    /**
     * 验证 tele 字段是否重复，如果重复，返回false
     *
     * @param tele 验证字段
     * @return 如果重复，返回false
     */
    @ApiOperation(value = "teleCheck", notes = "验证 tele 字段是否重复")
    @ApiResponses({
            @ApiResponse(code = 10011, message = "", response = Boolean.class),
            @ApiResponse(code = 10010, message = "电话已被注册", response = Boolean.class)
    })
    @GetMapping("/tele_check")
    public Result teleCheck(@RequestParam("tele") String tele) {
        Integer count = userService.hasSameTeleUser(tele);
        boolean flag = count == 0;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        String msg = flag ? "" : "电话已被注册";
        return new Result(flag, code, msg);
    }

    /**
     * 验证 qq 字段是否重复，如果重复，返回false
     *
     * @param qq 验证字段
     * @return 如果重复，返回false
     */
    @ApiOperation(value = "qqCheck", notes = "验证 qq 字段是否重复")
    @ApiResponses({
            @ApiResponse(code = 10011, message = "", response = Boolean.class),
            @ApiResponse(code = 10010, message = "QQ已被注册", response = Boolean.class)
    })
    @GetMapping("/qq_check")
    public Result qqCheck(@RequestParam("qq") String qq) {
        Integer count = userService.hasSameQQUser(qq);
        boolean flag = count == 0;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        String msg = flag ? "" : "QQ已被注册";
        return new Result(flag, code, msg);
    }

    /**
     * 验证登录，如果登录信息匹配则返回登录用户的对象
     * @param info 登录字段，可以是用户名，电话或者邮箱
     * @param password 密码
     * @return 如果登录信息匹配则返回登录用户的对象
     */
    @ApiOperation(value = "loginCheck", notes = "验证登录，如果登录信息匹配则返回登录用户的对象")
    @ApiResponses({
            @ApiResponse(code = 10011, message = "登陆成功", response = User.class),
            @ApiResponse(code = 10010, message = "登录失败，请重试")
    })
    @GetMapping("/login")
    public Result loginCheck(@RequestParam("info") String info, @RequestParam("password") String password) {
        User user = userService.loginUser(info, password);
        boolean flag = user != null;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        String msg = flag ? "登陆成功" : "登录失败，请重试";
        return new Result(user, code, msg);
    }
}
