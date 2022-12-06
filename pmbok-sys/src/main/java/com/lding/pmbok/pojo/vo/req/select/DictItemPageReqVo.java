package com.lding.pmbok.pojo.vo.req.select;

import com.lding.pmbok.pojo.vo.req.KeywordPageReqVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DictItemPageReqVo extends KeywordPageReqVo {
    // 数据字典类型的id
    private Integer typeId;
}
