package com.hotel.controller;

import com.hotel.domain.Room;
import com.hotel.service.RoomService;
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
public class RoomController {

    @Autowired
    private RoomService roomService;

    /**
     * 添加房间
     *
     * @param room 房间对象
     * @return 是否成功
     */
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
    @DeleteMapping("/{id}")
    public Result deleteRoomById(@PathVariable Integer id) {
        boolean flag = roomService.deleteById(id);
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
    @GetMapping
    public Result getAllRooms() {
        List<Room> rooms = roomService.getAll();
        boolean flag = rooms != null && rooms.size() > 0;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        String msg = flag ? "" : "房间查询失败，请重试";
        return new Result(rooms, code, msg);
    }

    /**
     * 查询所有在 hotelId 酒店下的房间
     *
     * @param id 酒店 id
     * @return 所有在 hotelId 酒店下的房间
     */
    @GetMapping("/h")
    public Result getRoomsByHotelId(@RequestParam("hotelId") Integer id) {
        List<Room> rooms = roomService.getByHotelId(id);
        boolean flag = rooms != null && rooms.size() > 0;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        String msg = flag ? "" : "房间查询失败，请重试";
        return new Result(rooms, code, msg);
    }

    /**
     * 查询所有 typeId 类型的房间
     *
     * @param id 类型 id
     * @return 所有 typeId 类型的房间
     */
    @GetMapping("/t")
    public Result getRoomsByType(@RequestParam("typeId") Integer id) {
        List<Room> rooms = roomService.getByType(id);
        boolean flag = rooms != null && rooms.size() > 0;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        String msg = flag ? "" : "房间查询失败，请重试";
        return new Result(rooms, code, msg);
    }

    /**
     * 查询在 hotelId 酒店中 typeId类型的房间
     *
     * @param hotelId 酒店 id
     * @param typeId  类型 id
     * @return 在 hotelId 酒店中 typeId类型的房间
     */
    @GetMapping("/ht")
    public Result getRoomsByHotelAndType(@RequestParam("hotelId") Integer hotelId,
                                         @RequestParam("typeId") Integer typeId) {
        List<Room> rooms = roomService.getByHotelAndType(hotelId, typeId);
        boolean flag = rooms != null && rooms.size() > 0;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        String msg = flag ? "" : "房间查询失败，请重试";
        return new Result(rooms, code, msg);
    }

    /**
     * 查询在 hotelId 酒店中状态为 status 的房间
     *
     * @param hotelId 酒店 id
     * @param status  状态
     * @return 在 hotelId 酒店中状态为 status 的房间
     */
    @GetMapping("/hs")
    public Result getRoomsByHotelAndStatus(@RequestParam("hotelId") Integer hotelId,
                                           @RequestParam("status") Integer status) {
        List<Room> rooms = roomService.getByHotelAndStatus(hotelId, status);
        boolean flag = rooms != null && rooms.size() > 0;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        String msg = flag ? "" : "房间查询失败，请重试";
        return new Result(rooms, code, msg);
    }

    /**
     * 查询在 hotelId 酒店中 typeId类型的 状态为 status 的房间
     *
     * @param hotelId 酒店 id
     * @param typeId  类型 id
     * @param status  状态
     * @return 在 hotelId 酒店中 typeId类型的 状态为 status 的房间
     */
    @GetMapping("/hts")
    public Result getRoomsByHotelAndTypeAndStatus(@RequestParam("hotelId") Integer hotelId,
                                                  @RequestParam("typeId") Integer typeId,
                                                  @RequestParam("status") Integer status) {
        List<Room> rooms = roomService.getByHotelAndTypeAndStatus(hotelId, typeId, status);
        boolean flag = rooms != null && rooms.size() > 0;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        String msg = flag ? "" : "房间查询失败，请重试";
        return new Result(rooms, code, msg);
    }

    /**
     * 查询在 hotelId 酒店中 position 房间
     *
     * @param hotelId  酒店 id
     * @param position 房间位置
     * @return 在 hotelId 酒店中 position 房间
     */
    @GetMapping("/hp")
    public Result getRoomByHotelAndPosition(@RequestParam("hotelId") Integer hotelId,
                                            @RequestParam("position") String position) {
        Room room = roomService.getByHotelAndPosition(hotelId, position);
        boolean flag = room != null;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        String msg = flag ? "" : "房间查询失败，请重试";
        return new Result(room, code, msg);
    }
}
