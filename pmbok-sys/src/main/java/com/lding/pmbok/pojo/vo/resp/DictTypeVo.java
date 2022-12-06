package com.lding.pmbok.pojo.vo.resp;

import lombok.Data;

/**
 * 数据字典类型Vo
 */
@Data
public class DictTypeVo {
    // id
    private Integer id;

    // 名称
    private String name;

    // 值
    private String value;

    // 简介
    private String intro;
}
