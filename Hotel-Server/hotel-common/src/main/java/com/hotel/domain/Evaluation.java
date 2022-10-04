package com.hotel.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Bittere_Gift
 */
@Getter
@Setter
@ToString
public class Evaluation {
    private Order order;
    /**
     * 评分,1-5
     */
    private Integer score;
    /**
     * 文字评价
     */
    private String context;
    /**
     * 图片评价
     */
    private String pic;
    /**
     * 视频评价
     */
    private String video;
}
