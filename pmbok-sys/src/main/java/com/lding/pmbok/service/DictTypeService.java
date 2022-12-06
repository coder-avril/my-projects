package com.lding.pmbok.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lding.pmbok.pojo.po.DictType;
import com.lding.pmbok.pojo.vo.PageVo;
import com.lding.pmbok.pojo.vo.req.select.DictTypePageReqVo;
import com.lding.pmbok.pojo.vo.resp.DictTypeVo;

public interface DictTypeService extends IService<DictType> {
    PageVo<DictTypeVo> getTargetList(DictTypePageReqVo reqVo);
}
