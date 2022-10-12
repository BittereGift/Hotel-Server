package com.hotel.controller;

import com.hotel.domain.Evaluation;
import com.hotel.domain.Room;
import com.hotel.domain.User;
import com.hotel.service.CollectService;
import com.hotel.service.RoomService;
import com.hotel.service.UserSerivce;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bittere_Gift
 */
@RestController
@Slf4j
@RequestMapping("/collects")
@Api(tags = "Collect")
public class CollectController {

    @Autowired
    private CollectService collectService;

    /**
     * 新收藏
     *
     * @param userId 用户 id
     * @param roomId 房间 id
     * @return 是否成功
     */
    @ApiOperation(value = "addCollect", notes = "收藏", response = Boolean.class)
    @ApiResponses({
            @ApiResponse(code = 10001, message = ""),
            @ApiResponse(code = 10000, message = "收藏添加失败，请重试")
    })
    @PostMapping
    public Result addCollect(@RequestParam("userId") Integer userId, @RequestParam("roomId") Integer roomId) {
        boolean flag = collectService.add(userId, roomId);
        Integer code = flag ? Code.POST_OK : Code.POST_FAIL;
        String msg = flag ? "" : "收藏添加失败，请重试";
        return new Result(flag, code, msg);
    }

    /**
     * 移除收藏
     *
     * @param userId 用户 id
     * @param roomId 房间 id
     * @return 是否成功
     */
    @ApiOperation(value = "deleteCollect", notes = "收藏", response = Boolean.class)
    @ApiResponses({
            @ApiResponse(code = 10021, message = ""),
            @ApiResponse(code = 10020, message = "收藏删除失败，请重试")
    })
    @DeleteMapping
    public Result deleteCollect(@RequestParam("userId") Integer userId, @RequestParam("roomId") Integer roomId) {
        boolean flag = collectService.delete(userId, roomId);
        Integer code = flag ? Code.DELETE_OK : Code.DELETE_FAIL;
        String msg = flag ? "" : "收藏删除失败，请重试";
        return new Result(flag, code, msg);
    }

    /**
     * 删除某个用户的全部收藏
     * @param userId
     */
    @ApiOperation(value = "deleteCollectsByUser", notes = "删除某个用户的全部收藏")
    @DeleteMapping("/de/{userId}")
    public void deleteCollectsByUser(@PathVariable Integer userId) {
        collectService.deleteByUserId(userId);
    }

    @ApiOperation(value = "deleteCollectsByUserAndRooms", notes = "删除用户指定的收藏", response = Boolean.class)
    @ApiResponses({
            @ApiResponse(code = 10021, message = ""),
            @ApiResponse(code = 10020, message = "收藏删除失败，请重试")
    })
    @DeleteMapping("/deurs")
    public Result deleteCollectsByUserAndRooms(@RequestParam("userId") Integer userId,
                                               @RequestParam("roomIds") List<Integer> roomIds) {
        boolean flag = collectService.deleteByUserAndRooms(userId, roomIds);
        Integer code = flag? Code.DELETE_OK : Code.DELETE_FAIL;
        String msg = flag? "" : "收藏删除失败，请重试";
        return new Result(flag, code, msg);
    }

    /**
     * 查询某一用户的所有收藏
     * @param userId 用户 id
     * @return 收藏的 roomId
     */
    @ApiOperation(value = "getCollectsOfUser", notes = "查询某一用户的所有收藏",
            response = Room.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 10011, message = ""),
            @ApiResponse(code = 10010, message = "")
    })
    @GetMapping("/u/{userId}")
    public Result getCollectsOfUser(@PathVariable Integer userId) {
        List<Room> rooms = collectService.getRoomIdsByUserId(userId);
        boolean flag = rooms != null && rooms.size() > 0;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        return new Result(rooms, code);
    }

    /**
     * 查询某一用户的收藏量
     * @param userId 用户 id
     * @return 收藏量
     */
    @ApiOperation(value = "getCollectCountOfUser", notes = "查询某一用户的收藏量",
            response = Integer.class)
    @ApiResponses({
            @ApiResponse(code = 10010, message = "")
    })
    @GetMapping("/uc/{userId}")
    public Result getCollectCountOfUser(@PathVariable Integer userId) {
        Integer count = collectService.getCollectCountOfUser(userId);
        return new Result(count, Code.GET_OK);
    }

    /**
     * 查询某一个房间被哪些客户收藏
     * @param roomId 房间 id
     * @return 客户 id
     */
    @ApiOperation(value = "getCollectsOfRoom", notes = "查询某一个房间被哪些客户收藏",
            response = User.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 10011, message = ""),
            @ApiResponse(code = 10010, message = "")
    })
    @GetMapping("/r/{roomId}")
    public Result getCollectsOfRoom(@PathVariable Integer roomId) {
        List<User> users = collectService.getUserIdsByRoomId(roomId);
        boolean flag = users != null && users.size() > 0;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        return new Result(users, code);
    }

    /**
     * 查询某个房间的被收藏量
     * @param roomId 房间 id
     * @return 收藏量
     */
    @ApiOperation(value = "getCollectCountOfRoom", notes = "查询某个房间的被收藏量",
            response = Integer.class)
    @ApiResponses({
            @ApiResponse(code = 10010, message = "")
    })
    @GetMapping("/rc/{roomId}")
    public Result getCollectCountOfRoom(@PathVariable Integer roomId) {
        Integer count = collectService.getCollectCountOfRoom(roomId);
        return new Result(count, Code.GET_OK);
    }
}
