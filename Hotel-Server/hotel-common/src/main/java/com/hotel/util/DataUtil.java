package com.hotel.util;

import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson2.JSON;
import com.hotel.domain.BookTime;

import java.util.Date;
import java.util.List;

/**
 * @author Bittere_Gift
 */
public class DataUtil {

    private DataUtil() {}

    /**
     * 将 Feign 传递过来的 data 转型成对应的类
     * @param data
     * @param clazz
     * @return
     * @param <T>
     */
    public static <T> T getDataFromFeign(Object data, Class<T> clazz) {
        String jsonUser = JSON.toJSONString(data);
        return JSON.parseObject(jsonUser, clazz);
    }

    /**
     * 判断预约时间是否可用
     * @param newBookTime 预约的时间
     * @param bookTimeList 已预约时间表
     * @return 是否能够预约
     */
    public static boolean isBookTimeValid(BookTime newBookTime, List<BookTime> bookTimeList) {
        Date startTime = newBookTime.getStartTime();
        Date endTime = newBookTime.getEndTime();
        if (bookTimeList.isEmpty()) {
            return true;
        }
        if (endTime.before(bookTimeList.get(0).getStartTime())) {
            return true;
        }
        if (startTime.after(bookTimeList.get(bookTimeList.size() - 1).getEndTime())) {
            return true;
        }
        for (int i = 0; i < bookTimeList.size() - 1; i++) {
            BookTime bookTime1 = bookTimeList.get(i);
            Date startTime1 = bookTime1.getStartTime();
            Date endTime1 = bookTime1.getEndTime();
            BookTime bookTime2 = bookTimeList.get(i + 1);
            Date startTime2 = bookTime2.getStartTime();
            Date endTime2 = bookTime2.getEndTime();
            if (startTime.after(endTime1) && startTime.before(startTime2) &&
                    endTime.after(endTime1) && endTime.before(startTime2)) {
                return true;
            }
        }
        return false;
    }
}
