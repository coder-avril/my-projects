package com.lding.pmbok.pojo.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 面向客户端的普通数据信息（额外附带msg）Vo
 *  一般用于返回给前端页面的数据（不用于页面的表格展示
 * @param <T>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DataJsonVo<T> extends JsonVo {
    private T data;

    public DataJsonVo() {
        // 保留默认的构造方法
    }

    public DataJsonVo(String msg, T data) {
        super(true, msg);
        this.data = data;
    }

    public DataJsonVo(T data) {
        // 只保留数据时，默认是成功
        this(null, data);
    }
}
