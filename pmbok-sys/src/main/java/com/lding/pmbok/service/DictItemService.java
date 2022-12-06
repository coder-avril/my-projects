package com.lding.pmbok.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lding.pmbok.pojo.po.DictItem;
import com.lding.pmbok.pojo.vo.PageVo;
import com.lding.pmbok.pojo.vo.req.select.DictItemPageReqVo;
import com.lding.pmbok.pojo.vo.resp.DictItemVo;

// 配合BaseMapper 一旦继承了MybatisPlus的IService<PO>类 就可以自动实现当前Mapper对应的table的增删改查
public interface DictItemService extends IService<DictItem> {
    PageVo<DictItemVo> getTargetList(DictItemPageReqVo query);
}
