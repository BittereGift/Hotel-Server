package com.hotel.controller;

import com.hotel.domain.User;
import com.hotel.service.UserService;
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
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public Result addUser(@RequestBody User user) {
        boolean flag = userService.addUser(user);
        Integer code = flag ? Code.POST_OK : Code.POST_FAIL;
        String msg = flag ? "" : "用户新建失败，请重试";
        Result result = new Result(flag, code, msg);
        log.info("User saved, result: {}", result);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result deleteUserById(@PathVariable("id") Integer id) {
        boolean flag = userService.deleteUserById(id);
        Integer code = flag ? Code.DELETE_OK : Code.DELETE_FAIL;
        Result result = new Result(flag, code);
        log.info("User deleting, result: {}", result);
        return result;
    }

    @PutMapping
    public Result updateUser(@RequestBody User user) {
        boolean flag = userService.update(user);
        Integer code = flag ? Code.PUT_OK : Code.PUT_FAIL;
        Result result = new Result(flag, code);
        log.info("User updating, result: {}", result);
        return result;
    }

    @GetMapping("/{id}")
    public Result getUserById(@PathVariable("id") Integer id) {
        User user = userService.getById(id);
        boolean flag = user != null;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        String msg = flag ? "" : "用户查询失败，请重试";
        Result result = new Result(user, code, msg);
        log.info("User getting, result: {}", result);
        return result;
    }

    @GetMapping
    public Result getAllUsers() {
        List<User> userList = userService.getAll();
        boolean flag = userList != null;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        String msg = flag ? "" : "用户查询失败，请重试";
        Result result = new Result(userList, code, msg);
        log.info("Users getting, result: {}", result);
        return result;
    }

    /**
     * 验证 name 字段是否重复，如果重复，返回false
     *
     * @param name 验证字段
     * @return 如果重复，返回false
     */
    @GetMapping("/name_check")
    public Result nameCheck(@RequestParam("name") String name) {
        Integer count = userService.hasSameNameUser(name);
        boolean flag = count == 0;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        String msg = flag ? "" : "用户名已被注册";
        Result result = new Result(flag, code, msg);
        log.info("hasSameNameUser, result: {}", result);
        return result;
    }

    /**
     * 验证 email 字段是否重复，如果重复，返回false
     *
     * @param email 验证字段
     * @return 如果重复，返回false
     */
    @GetMapping("/email_check")
    public Result emailCheck(@RequestParam("email") String email) {
        Integer count = userService.hasSameEmailUser(email);
        boolean flag = count == 0;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        String msg = flag ? "" : "邮箱已被注册";
        Result result = new Result(flag, code, msg);
        log.info("hasSameEmailUser, result: {}", result);
        return result;
    }

    /**
     * 验证 tele 字段是否重复，如果重复，返回false
     *
     * @param tele 验证字段
     * @return 如果重复，返回false
     */
    @GetMapping("/tele_check")
    public Result teleCheck(@RequestParam("tele") String tele) {
        Integer count = userService.hasSameTeleUser(tele);
        boolean flag = count == 0;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        String msg = flag ? "" : "电话已被注册";
        Result result = new Result(flag, code, msg);
        log.info("hasSameTeleUser, result: {}", result);
        return result;
    }

    /**
     * 验证 qq 字段是否重复，如果重复，返回false
     *
     * @param qq 验证字段
     * @return 如果重复，返回false
     */
    @GetMapping("/qq_check")
    public Result qqCheck(@RequestParam("qq") String qq) {
        Integer count = userService.hasSameQQUser(qq);
        boolean flag = count == 0;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        String msg = flag ? "" : "QQ已被注册";
        Result result = new Result(flag, code, msg);
        log.info("hasSameQQUser, result: {}", result);
        return result;
    }

    /**
     * 验证登录，如果登录信息匹配则返回登录用户的对象
     * @param info 登录字段，可以是用户名，电话或者邮箱
     * @param password 密码
     * @return 如果登录信息匹配则返回登录用户的对象
     */
    @GetMapping("/login")
    public Result loginCheck(@RequestParam("info") String info, @RequestParam("password") String password) {
        User user = userService.loginUser(info, password);
        boolean flag = user != null;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        String msg = flag ? "登陆成功" : "登录失败，请重试";
        Result result = new Result(user, code, msg);
        log.info("User login, result: {}", result);
        return result;
    }
}
