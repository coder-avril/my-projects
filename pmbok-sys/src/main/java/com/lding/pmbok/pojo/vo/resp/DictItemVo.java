package com.lding.pmbok.pojo.vo.resp;

import lombok.Data;

/**
 * 数据字典条目
 */
@Data
public class DictItemVo {
    // id
    private Integer id;

    // 名称
    private String name;

    // 值
    private String value;

    // 序号【值越大，就排在越前面】
    private Integer sn;

    // 是否禁用【0代表不禁用（启用），1代表禁用】
    private Short disabled;

    // 数据字典类型的id
    private Integer typeId;
}
