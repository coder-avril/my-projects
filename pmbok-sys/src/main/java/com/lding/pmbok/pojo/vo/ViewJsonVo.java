package com.lding.pmbok.pojo.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

/**
 * 面向客户端的分页信息Vo
 *  一般用于返回给前端页面的表格（带有分页的那种）（多见于list之类的方法中
 * @param <T>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ViewJsonVo<T> extends DataJsonVo<List<T>> {
    // 总数
    private Long count;
}
