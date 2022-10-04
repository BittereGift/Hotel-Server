package com.hotel.controller;

import com.hotel.domain.Room;
import com.hotel.domain.User;
import com.hotel.service.RoomService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Bittere_Gift
 */
@RestController
@Slf4j
@RequestMapping("/rooms")
@Api(tags = "Room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    /**
     * 添加房间
     *
     * @param room 房间对象
     * @return 是否成功
     */
    @ApiOperation(value = "addRoom", notes = "添加房间", response = Boolean.class)
    @ApiResponses({
            @ApiResponse(code = 10001, message = ""),
            @ApiResponse(code = 10000, message = "房间添加失败，请重试")
    })
    @PostMapping
    public Result addRoom(@RequestBody Room room) {
        boolean flag = roomService.addRoom(room);
        Integer code = flag ? Code.POST_OK : Code.POST_FAIL;
        String msg = flag ? "" : "房间添加失败，请重试";
        return new Result(flag, code, msg);
    }

    /**
     * 删除房间
     *
     * @param id id
     * @return 是否成功
     */
    @ApiOperation(value = "deleteRoomById", notes = "删除房间", response = Boolean.class)
    @ApiResponses({
            @ApiResponse(code = 10021, message = ""),
            @ApiResponse(code = 10020, message = "删除失败，请重试")
    })
    @DeleteMapping("/{id}")
    public Result deleteRoomById(@PathVariable Integer id) {
        boolean flag = roomService.deleteById(id);
        Integer code = flag ? Code.DELETE_OK : Code.DELETE_FAIL;
        String msg = flag ? "" : "房间删除失败，请重试";
        return new Result(flag, code, msg);
    }

    /**
     * 删除某个酒店所有的房间
     *
     * @param hotelId 酒店 id
     * @return 是否成功
     */
    @ApiOperation(value = "deleteRoomByHotel", notes = "删除某个酒店所有的房间", response = Boolean.class)
    @ApiResponses({
            @ApiResponse(code = 10021, message = ""),
            @ApiResponse(code = 10020, message = "删除失败，请重试")
    })
    @DeleteMapping("/h/{hotelId}")
    public Result deleteRoomByHotel(@PathVariable Integer hotelId) {
        boolean flag = roomService.deleteByHotel(hotelId);
        Integer code = flag ? Code.DELETE_OK : Code.DELETE_FAIL;
        String msg = flag ? "" : "房间删除失败，请重试";
        return new Result(flag, code, msg);
    }

    /**
     * 更改信息
     *
     * @param room 更改后的对象信息实例
     * @return 是否成功
     */
    @PutMapping
    @ApiOperation(value = "updateRoom", notes = "更改房间信息", response = Boolean.class)
    @ApiResponses({
            @ApiResponse(code = 10031, message = ""),
            @ApiResponse(code = 10030, message = "更新失败，请重试")
    })
    public Result updateRoom(@RequestBody Room room) {
        boolean flag = roomService.update(room);
        Integer code = flag ? Code.PUT_OK : Code.PUT_FAIL;
        String msg = flag ? "" : "房间更新失败，请重试";
        return new Result(flag, code, msg);
    }

    /**
     * 通过 id 更改房间的状态
     *
     * @param id     房间 id
     * @param status 房间的状态
     * @return 是否成功
     */
    @ApiOperation(value = "updateStatus", notes = "更改房间状态", response = Boolean.class)
    @ApiResponses({
            @ApiResponse(code = 10031, message = ""),
            @ApiResponse(code = 10030, message = "更新失败，请重试")
    })
    @PutMapping("/us")
    public Result updateStatus(@RequestParam("id") Integer id, @RequestParam("status") Integer status) {
        boolean flag = roomService.updateStatus(id, status);
        Integer code = flag ? Code.PUT_OK : Code.PUT_FAIL;
        String msg = flag ? "" : "房间更新失败，请重试";
        return new Result(flag, code, msg);
    }

    /**
     * 通过 id 查询信息
     *
     * @param id id
     * @return 查询到的对象信息实例
     */
    @ApiOperation(value = "getRoomById", notes = "查询房间信息", response = Room.class)
    @ApiResponses({
            @ApiResponse(code = 10011, message = ""),
            @ApiResponse(code = 10010, message = "房间查询失败，请重试")
    })
    @GetMapping("/{id}")
    public Result getRoomById(@PathVariable Integer id) {
        Room room = roomService.getById(id);
        boolean flag = room != null;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        String msg = flag ? "" : "房间查询失败，请重试";
        return new Result(room, code, msg);
    }

    /**
     * 查询所有对象的信息
     *
     * @return 所用实例的 List 集合
     */
    @ApiOperation(value = "getAllRooms", notes = "查询所有房间信息", response = Room.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 10011, message = ""),
            @ApiResponse(code = 10010, message = "房间查询失败，请重试")
    })
    @GetMapping
    public Result getAllRooms() {
        List<Room> rooms = roomService.getAll();
        boolean flag = rooms != null && rooms.size() > 0;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        return new Result(rooms, code);
    }

    /**
     * 通过 id 数组获取 room 集合
     *
     * @param ids ids
     * @return rooms
     */
    @ApiOperation(value = "getRoomsByIds", notes = "通过 id 数组获取 room 集合", response = Room.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 10011, message = ""),
            @ApiResponse(code = 10010, message = "房间查询失败，请重试")
    })
    @GetMapping("/ids")
    public Result getRoomsByIds(@RequestParam("ids") List<Integer> ids) {
        List<Room> rooms = roomService.getByIds(ids);
        boolean flag = rooms != null && rooms.size() > 0;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        return new Result(rooms, code);
    }

    /**
     * 查询在 hotelId 酒店中 typeId类型的 状态为 status 位置为 position 的房间，不输入条件则为不限制该条件
     *
     * @param hotelId  酒店 id
     * @param typeId   类型 id
     * @param status   状态
     * @param position 房间位置
     * @return 在 hotelId 酒店中 typeId类型的 状态为 status 位置为 position 的房间
     */
    @ApiOperation(value = "getRoomsByHotelAndTypeAndStatusAndPosition",
            notes = "查询在 hotelId 酒店中 typeId类型的 状态为 status 位置为 position 的房间，不输入条件则为不限制该条件",
            response = Room.class,
            responseContainer = "List"
    )
    @ApiResponses({
            @ApiResponse(code = 10011, message = ""),
            @ApiResponse(code = 10010, message = "房间查询失败，请重试")
    })
    @GetMapping("/s")
    public Result getRoomsByHotelAndTypeAndStatusAndPosition(@RequestParam(value = "hotelId", required = false) Integer hotelId,
                                                             @RequestParam(value = "typeId", required = false) Integer typeId,
                                                             @RequestParam(value = "status", required = false) Integer status,
                                                             @RequestParam(value = "position", required = false) String position) {
        List<Room> rooms = roomService.getByHotelAndTypeAndStatusAndPosition(hotelId, typeId, status, position);
        boolean flag = rooms != null && rooms.size() > 0;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        return new Result(rooms, code);
    }
}
