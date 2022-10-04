package com.hotel.controller;

import com.hotel.domain.Hotel;
import com.hotel.domain.Room;
import com.hotel.service.HotelService;
import com.hotel.service.RoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Bittere_Gift
 */
@RestController
@RequestMapping("/hotels")
@Api(tags = "Hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RoomService roomService;

    /**
     * 添加酒店
     *
     * @param hotel 酒店
     * @return 是否成功
     */
    @ApiOperation(value = "addHotel", notes = "添加酒店", response = Boolean.class)
    @ApiResponses({
            @ApiResponse(code = 10001, message = ""),
            @ApiResponse(code = 10000, message = "酒店添加失败，请重试")
    })
    @PostMapping
    public Result addHotel(@RequestBody Hotel hotel) {
        boolean flag = hotelService.add(hotel);
        Integer code = flag ? Code.POST_OK : Code.POST_FAIL;
        String msg = flag ? "" : "酒店添加失败，请重试";
        return new Result(flag, code, msg);
    }

    /**
     * 删除酒店
     *
     * @param id 酒店 id
     * @return 是否成功
     */
    @ApiOperation(value = "deleteHotel", notes = "删除酒店", response = Boolean.class)
    @ApiResponses({
            @ApiResponse(code = 10021, message = ""),
            @ApiResponse(code = 10020, message = "酒店删除失败，请重试")
    })
    @DeleteMapping("/{id}")
    public Result deleteHotel(@PathVariable Integer id) {
        boolean flag = hotelService.delete(id);
        if (flag) {
            roomService.deleteHotelRooms(id);
        }
        Integer code = flag ? Code.DELETE_OK : Code.DELETE_FAIL;
        String msg = flag ? "" : "酒店删除失败，请重试";
        return new Result(flag, code, msg);
    }

    /**
     * 更改酒店信息
     *
     * @param hotel 酒店
     * @return 是否成功
     */
    @ApiOperation(value = "updateHotel", notes = "更改更新信息", response = Boolean.class)
    @ApiResponses({
            @ApiResponse(code = 10031, message = ""),
            @ApiResponse(code = 10030, message = "酒店更新失败，请重试")
    })
    @PutMapping
    public Result updateHotel(@RequestBody Hotel hotel) {
        boolean flag = hotelService.update(hotel);
        Integer code = flag ? Code.PUT_OK : Code.PUT_FAIL;
        String msg = flag ? "" : "酒店更新失败，请重试";
        return new Result(flag, code, msg);
    }

    /**
     * 通过 id 查询酒店
     *
     * @param id id
     * @return 酒店
     */
    @ApiOperation(value = "getHotelById", notes = "查询酒店信息", response = Hotel.class)
    @ApiResponses({
            @ApiResponse(code = 10011, message = ""),
            @ApiResponse(code = 10010, message = "酒店查询失败，请重试")
    })
    @GetMapping("/{id}")
    public Result getHotelById(@PathVariable Integer id) {
        Hotel hotel = hotelService.getById(id);
        boolean flag = hotel != null;
        if (flag) {
            hotel.setRoomList((List<Room>) roomService.getRoomList(id).getData());
        }
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        String msg = flag ? "" : "酒店查询失败，请重试";
        return new Result(hotel, code, msg);
    }

    /**
     * 模糊查询酒店
     *
     * @param name    模糊名字
     * @param address 模糊位置
     * @return 酒店
     */
    @ApiOperation(value = "getLikeNameAndLikeAddress", notes = "模糊查询酒店", response = Hotel.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 10011, message = ""),
            @ApiResponse(code = 10010, message = "酒店查询失败，请重试")
    })
    @GetMapping("/s")
    public Result getLikeNameAndLikeAddress(@RequestParam(value = "name", required = false) String name,
                                            @RequestParam(value = "address", required = false) String address) {
        String likeName = name == null ? null : "%" + name + "%";
        String likeAddress = address == null ? null : "%" + address + "%";
        List<Hotel> hotelList = hotelService.getLikeNameAndLikeAddress(likeName, likeAddress);
        boolean flag = hotelList != null && hotelList.size() > 0;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        return new Result(hotelList, code);
    }
}
