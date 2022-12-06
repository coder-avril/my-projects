package com.lding.pmbok.pojo.vo.resp;

import lombok.Data;
import java.util.List;

/**
 * 树状结构的资源
 */
@Data
public class SysResourceTreeVo {
    // id
    private Integer id;

    // 名称
    private String title;

    // 默认是否展开
    private boolean spread = true;

    // 子资源
    private List<SysResourceTreeVo> children;
}
