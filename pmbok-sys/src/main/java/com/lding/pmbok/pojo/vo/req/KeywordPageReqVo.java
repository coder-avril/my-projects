package com.lding.pmbok.pojo.vo.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class KeywordPageReqVo extends PageReqVo {
    // 搜索关键词
    private String keyword;
}
