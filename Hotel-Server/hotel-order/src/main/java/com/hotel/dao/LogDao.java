package com.hotel.dao;

import com.hotel.domain.Status;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * @author Bittere_Gift
 */
@Mapper
public interface LogDao {

    /**
     * 新增日志
     *
     * @param orderId 订单 id
     * @param info 日志信息
     */
    @Insert("insert into order_status_log values (#{orderId}, #{info}, now())")
    void add(Integer orderId, String info);

    /**
     * 通过订单 id 查询日志
     * @param orderId 订单 id
     * @return 日志
     */
    @Select("select * from order_status_log where order_id = #{orderId}")
    List<Status> getLogsByOrder(Integer orderId);
}
