package com.hotel.controller;

import com.hotel.domain.Order;
import com.hotel.domain.Room;
import com.hotel.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author Bittere_Gift
 */
@RestController
@Slf4j
@RequestMapping("/orders")
@Api(tags = "Order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 新增订单
     *
     */
    @ApiOperation(value = "addOrder", notes = "新增订单", response = Boolean.class)
    @ApiResponses({
            @ApiResponse(code = 10001, message = ""),
            @ApiResponse(code = 10000, message = "订单添加失败，请重试")
    })
    @PostMapping
    public Result addOrder(@RequestParam("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startTime,
                           @RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endTime,
                           @RequestParam("userId") Integer userId,
                           @RequestParam("roomId") Integer roomId) {
        Order newOrder = orderService.addOrder(startTime, endTime, userId, roomId);
        boolean flag = newOrder != null;
        Integer code = flag ? Code.POST_OK : Code.POST_FAIL;
        String msg = flag ? "" : "订单添加失败，请重试";
        return new Result(newOrder, code, msg);
    }

    /**
     * 修改订单状态
     *
     * @param orderId   订单 id
     * @param newStatus 新的状态
     * @return 修改成功
     */
    @ApiOperation(value = "updateStatus", notes = "修改订单状态", response = Boolean.class)
    @ApiResponses({
            @ApiResponse(code = 10031, message = ""),
            @ApiResponse(code = 10030, message = "订单更新失败，请重试")
    })
    @PutMapping("/up/status")
    public Result updateStatus(@RequestParam("orderId") Integer orderId,
                               @RequestParam("newStatus") String newStatus) {
        boolean flag = orderService.updateStatus(orderId, newStatus);
        Integer code = flag? Code.PUT_OK : Code.PUT_FAIL;
        String msg = flag? "" : "订单更新失败，请重试";
        return new Result(flag, code, msg);
    }

    /**
     * 修改订单时间
     *
     * @param orderId   订单 id
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 修改成功
     */
    @ApiOperation(value = "updateTime", notes = "修改订单时间", response = Boolean.class)
    @ApiResponses({
            @ApiResponse(code = 10031, message = ""),
            @ApiResponse(code = 10030, message = "订单更新失败，请重试")
    })
    @PutMapping("/up/time")
    public Result updateTime(@RequestParam("orderId") Integer orderId,
                               @RequestParam("startTime") Date startTime,
                               @RequestParam("endTime") Date endTime) {
        boolean flag = orderService.updateTime(orderId, startTime, endTime);
        Integer code = flag? Code.PUT_OK : Code.PUT_FAIL;
        String msg = flag? "" : "订单更新失败，请重试";
        return new Result(flag, code, msg);
    }

    /**
     * 修改订单房间
     *
     * @param orderId 订单 id
     * @param newRoom 房间
     * @return 修改成功
     */
    @ApiOperation(value = "updateRoom", notes = "修改订单房间", response = Boolean.class)
    @ApiResponses({
            @ApiResponse(code = 10031, message = ""),
            @ApiResponse(code = 10030, message = "订单更新失败，请重试")
    })
    @PutMapping("/up/room")
    public Result updateRoom(@RequestParam("orderId") Integer orderId,
                             @RequestBody Room newRoom) {
        boolean flag = orderService.updateRoom(orderId, newRoom);
        Integer code = flag? Code.PUT_OK : Code.PUT_FAIL;
        String msg = flag? "" : "订单更新失败，请重试";
        return new Result(flag, code, msg);
    }

    /**
     * 通过 id 查询订单
     *
     * @param id id
     * @return 订单
     */
    @ApiOperation(value = "getOrderById", notes = "查询订单信息", response = Order.class)
    @ApiResponses({
            @ApiResponse(code = 10011, message = ""),
            @ApiResponse(code = 10010, message = "订单查询失败，请重试")
    })
    @GetMapping("/{id}")
    public Result getOrderById(@PathVariable Integer id) {
        Order order = orderService.getById(id);
        boolean flag = order != null;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        String msg = flag ? "" : "订单查询失败，请重试";
        return new Result(order, code, msg);
    }

    /**
     * 查询全部
     *
     * @return 订单
     */
    @ApiOperation(value = "getAllOrders", notes = "查询全部",
            response = Order.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 10011, message = ""),
            @ApiResponse(code = 10010, message = "")
    })
    @GetMapping
    public Result getAllOrders() {
        List<Order> orders = orderService.getAll();
        boolean flag = orders != null;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        return new Result(orders, code);
    }

    /**
     * 查询某个酒店所有的订单
     *
     * @param hotelId 酒店 id
     * @return 订单
     */
    @ApiOperation(value = "getOrdersByHotel", notes = "查询某个酒店所有的订单",
            response = Order.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 10011, message = ""),
            @ApiResponse(code = 10010, message = "")
    })
    @GetMapping("/h/{hotelId}")
    public Result getOrdersByHotel(@PathVariable Integer hotelId) {
        List<Order> orders = orderService.getByHotel(hotelId);
        boolean flag = orders != null;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        return new Result(orders, code);
    }

    /**
     * 查询某个用户的订单
     *
     * @param userId 用户 id
     * @return 订单
     */
    @ApiOperation(value = "getOrdersByUser", notes = "查询某个用户的订单",
            response = Order.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 10011, message = ""),
            @ApiResponse(code = 10010, message = "")
    })
    @GetMapping("/u/{userId}")
    public Result getOrdersByUser(@PathVariable Integer userId) {
        List<Order> orders = orderService.getByUser(userId);
        boolean flag = orders != null;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        return new Result(orders, code);
    }

    /**
     * 查询某个用户状态为 status 的订单
     *
     * @param userId 用户 id
     * @param status 状态
     * @return 订单
     */
    @ApiOperation(value = "getOrdersByUserAndStatus", notes = "查询某个用户状态为 status 的订单",
            response = Order.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 10011, message = ""),
            @ApiResponse(code = 10010, message = "")
    })
    @GetMapping("/us")
    public Result getOrdersByUserAndStatus(@RequestParam("userId") Integer userId,
                                           @RequestParam("status") String status) {
        List<Order> orders = orderService.getByUserAndStatus(userId, status);
        boolean flag = orders != null;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        return new Result(orders, code);
    }

    /**
     * 通过酒店名称模糊查询用户订单
     *
     * @param userId        用户 id
     * @param likeHotelName 模糊的酒店名称
     * @return 订单
     */
    @ApiOperation(value = "getOrdersByUserAndLikeHotelName", notes = "通过酒店名称模糊查询用户订单",
            response = Order.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 10011, message = ""),
            @ApiResponse(code = 10010, message = "")
    })
    @GetMapping("/un")
    public Result getOrdersByUserAndLikeHotelName(@RequestParam("userId") Integer userId,
                                                  @RequestParam("hotelName") String likeHotelName) {
        List<Order> orders = orderService.getByUserAndLikeHotelName(userId, likeHotelName);
        boolean flag = orders != null;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        return new Result(orders, code);
    }
}
