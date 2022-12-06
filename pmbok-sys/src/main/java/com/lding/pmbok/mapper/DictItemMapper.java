package com.lding.pmbok.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lding.pmbok.pojo.po.DictItem;

public interface DictItemMapper extends BaseMapper<DictItem> {
    // 一旦继承mybatisPlus的BaseMapper<PO> 就可以自动实现当前Mapper对应的table的增删改查
}
