package com.lding.pmbok.pojo.vo;

import lombok.Data;
import java.util.List;

/**
 * 用于存储从DB得到的分页数据
 * @param <T>
 */
@Data
public class PageVo<T> {
    // 总数
    private Long count;

    // 总页数
    private Long pages;

    // 数据
    private List<T> data;
}
