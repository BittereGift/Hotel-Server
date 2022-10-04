package com.hotel.controller;

import com.hotel.domain.Evaluation;
import com.hotel.service.EvalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Bittere_Gift
 */
@RestController
@Slf4j
@RequestMapping("/evaluations")
@Api("Eval")
public class EvalController {

    @Autowired
    private EvalService evalService;

    /**
     * 新增评论
     *
     * @param eval 评论
     * @return 是否成功
     */
    @ApiOperation(value = "addEval", notes = "添加评论", response = Boolean.class)
    @ApiResponses({
            @ApiResponse(code = 10001, message = ""),
            @ApiResponse(code = 10000, message = "评论添加失败，请重试")
    })
    @PostMapping
    public Result addEval(@RequestBody Evaluation eval) {
        boolean flag = evalService.add(eval);
        Integer code = flag ? Code.POST_OK : Code.POST_FAIL;
        String msg = flag ? "" : "评论添加失败，请重试";
        return new Result(flag, code, msg);
    }

    /**
     * 删除评论
     *
     * @param orderId 订单 id
     * @return 是否成功
     */
    @ApiOperation(value = "deleteEval", notes = "删除评论", response = Boolean.class)
    @ApiResponses({
            @ApiResponse(code = 10021, message = ""),
            @ApiResponse(code = 10020, message = "评论删除失败，请重试")
    })
    @DeleteMapping("/{orderId}")
    public Result deleteEval(@PathVariable Integer orderId) {
        boolean flag = evalService.delete(orderId);
        Integer code = flag ? Code.DELETE_OK : Code.DELETE_FAIL;
        String msg = flag ? "" : "评论删除失败，请重试";
        return new Result(flag, code, msg);
    }

    /**
     * 更新评论
     *
     * @param eval 新评论
     * @return 是否成功
     */
    @ApiOperation(value = "updateEval", notes = "更改评论信息", response = Boolean.class)
    @ApiResponses({
            @ApiResponse(code = 10031, message = ""),
            @ApiResponse(code = 10030, message = "评论修改失败，请重试")
    })
    @PutMapping
    public Result updateEval(@RequestBody Evaluation eval) {
        boolean flag = evalService.update(eval);
        Integer code = flag ? Code.PUT_OK : Code.PUT_FAIL;
        String msg = flag ? "" : "评论修改失败，请重试";
        return new Result(flag, code, msg);
    }

    /**
     * 通过订单 id 查询评论
     *
     * @param orderId 订单 id
     * @return 评论
     */
    @ApiOperation(value = "getEvalById", notes = "查询评论信息", response = Evaluation.class)
    @ApiResponses({
            @ApiResponse(code = 10011, message = ""),
            @ApiResponse(code = 10010, message = "评论查询失败，请重试")
    })
    @GetMapping("/{orderId}")
    public Result getEvalById(@PathVariable Integer orderId) {
        Evaluation eval = evalService.getById(orderId);
        boolean flag = eval != null;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        String msg = flag ? "" : "评论查询失败，请重试";
        return new Result(eval, code, msg);
    }

    /**
     * 查询所有评论
     *
     * @return 所有评论
     */
    @ApiOperation(value = "getAllEvals", notes = "查询所有评论", response = Evaluation.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 10011, message = ""),
            @ApiResponse(code = 10010, message = "评论查询失败，请重试")
    })
    @GetMapping
    public Result getAllEvals() {
        List<Evaluation> evalList = evalService.getAll();
        boolean flag = evalList != null && evalList.size() > 0;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        String msg = flag ? "" : "评论查询失败，请重试";
        return new Result(evalList, code, msg);
    }

    /**
     * 通过房间号查询评论
     *
     * @param roomId 房间号
     * @return 评论
     */
    @ApiOperation(value = "getEvalsByRoom", notes = "通过房间号查询评论", response = Evaluation.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 10011, message = ""),
            @ApiResponse(code = 10010, message = "")
    })
    @GetMapping("/r/{roomId}")
    public Result getEvalsByRoom(@PathVariable Integer roomId) {
        List<Evaluation> evalList = evalService.getByRoom(roomId);
        boolean flag = evalList != null && evalList.size() > 0;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        return new Result(evalList, code);
    }

    /**
     * 获取某个房间的平均分
     *
     * @param roomId 房价 id
     * @return 平均分
     */
    @ApiOperation(value = "getAvgScoreOfRoom", notes = "获取某个房间的平均分", response = Float.class)
    @ApiResponses({
            @ApiResponse(code = 10011, message = ""),
            @ApiResponse(code = 10010, message = "该房间还没有评分")
    })
    @GetMapping("/r/avg/{roomId}")
    public Result getAvgScoreOfRoom(@PathVariable Integer roomId) {
        Float score = evalService.getAverageScoreOfRoom(roomId);
        boolean flag = score != null;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        String msg = flag? "" : "该房间还没有评分";
        return new Result(score, code, msg);
    }

    /**
     * 查询某个房间评分低于指定分数的评论
     *
     * @param roomId 房间号
     * @return 评论
     */
    @ApiOperation(value = "getEvalsLowerThanGivenScoreOfRoom", notes = "查询某个房间评分低于指定分数的评论",
            response = Evaluation.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 10011, message = ""),
            @ApiResponse(code = 10010, message = "")
    })
    @GetMapping("/r/lor")
    public Result getEvalsLowerThanGivenScoreOfRoom(@RequestParam("roomId") Integer roomId,
                                                    @RequestParam("score") Integer score) {
        List<Evaluation> evalList = evalService.getScoreLowerThanGivenOfRoom(roomId, score);
        boolean flag = evalList != null && evalList.size() > 0;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        return new Result(evalList, code);
    }

    /**
     * 查询某个房间评分高于指定分数的评论
     *
     * @param roomId 房间号
     * @return 评论
     */
    @ApiOperation(value = "getEvalsUpperThanGivenScoreOfRoom", notes = "查询某个房间评分高于指定分数的评论",
            response = Evaluation.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 10011, message = ""),
            @ApiResponse(code = 10010, message = "")
    })
    @GetMapping("/r/upr")
    public Result getEvalsUpperThanGivenScoreOfRoom(@RequestParam("roomId") Integer roomId,
                                                    @RequestParam("score") Integer score) {
        List<Evaluation> evalList = evalService.getScoreHigherThanGivenOfRoom(roomId, score);
        boolean flag = evalList != null && evalList.size() > 0;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        return new Result(evalList, code);
    }

    /**
     * 通过酒店查询评论
     *
     * @param hotelId 酒店
     * @return 评论
     */
    @ApiOperation(value = "getEvalsByHotel", notes = "通过酒店查询评论",
            response = Evaluation.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 10011, message = ""),
            @ApiResponse(code = 10010, message = "")
    })
    @GetMapping("/h/{hotelId}")
    public Result getEvalsByHotel(@PathVariable Integer hotelId) {
        List<Evaluation> evalList = evalService.getByRoom(hotelId);
        boolean flag = evalList != null && evalList.size() > 0;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        return new Result(evalList, code);
    }

    /**
     * 获取某个酒店的平均分
     *
     * @param hotelId 酒店 id
     * @return 平均分
     */
    @ApiOperation(value = "getAvgScoreOfHotel", notes = "获取某个酒店的平均分", response = Float.class)
    @ApiResponses({
            @ApiResponse(code = 10011, message = ""),
            @ApiResponse(code = 10010, message = "该房间还没有评分")
    })
    @GetMapping("/h/avg/{hotelId}")
    public Result getAvgScoreOfHotel(@PathVariable Integer hotelId) {
        Float score = evalService.getAverageScoreOfHotel(hotelId);
        boolean flag = score != null;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        String msg = flag? "" : "该房间还没有评分";
        return new Result(score, code, msg);
    }

    /**
     * 查询某个酒店评分低于指定分数的评论
     *
     * @param hotelId 酒店号
     * @return 评论
     */
    @ApiOperation(value = "getEvalsLowerThanGivenScoreOfHotel", notes = "查询某个酒店评分低于指定分数的评论",
            response = Evaluation.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 10011, message = ""),
            @ApiResponse(code = 10010, message = "")
    })
    @GetMapping("/h/lor")
    public Result getEvalsLowerThanGivenScoreOfHotel(@RequestParam("hotelId") Integer hotelId,
                                                     @RequestParam("score") Integer score) {
        List<Evaluation> evalList = evalService.getScoreLowerThanGivenOfHotel(hotelId, score);
        boolean flag = evalList != null && evalList.size() > 0;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        return new Result(evalList, code);
    }

    /**
     * 查询某个酒店评分高于指定分数的评论
     *
     * @param hotelId 酒店号
     * @return 评论
     */
    @ApiOperation(value = "getEvalsUpperThanGivenScoreOfHotel", notes = "查询某个酒店评分高于指定分数的评论",
            response = Evaluation.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 10011, message = ""),
            @ApiResponse(code = 10010, message = "")
    })
    @GetMapping("/h/upr")
    public Result getEvalsUpperThanGivenScoreOfHotel(@RequestParam("hotelId") Integer hotelId,
                                                     @RequestParam("score") Integer score) {
        List<Evaluation> evalList = evalService.getScoreHigherThanGivenOfHotel(hotelId, score);
        boolean flag = evalList != null && evalList.size() > 0;
        Integer code = flag ? Code.GET_OK : Code.GET_FAIL;
        return new Result(evalList, code);
    }
}
