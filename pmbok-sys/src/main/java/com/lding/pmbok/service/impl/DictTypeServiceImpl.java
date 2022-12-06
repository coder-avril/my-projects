package com.lding.pmbok.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lding.pmbok.common.enhance.MyLambdaQueryWrapper;
import com.lding.pmbok.common.enhance.MyPage;
import com.lding.pmbok.common.mapStruct.MapStructs;
import com.lding.pmbok.mapper.DictTypeMapper;
import com.lding.pmbok.pojo.po.DictType;
import com.lding.pmbok.pojo.vo.PageVo;
import com.lding.pmbok.pojo.vo.req.select.DictTypePageReqVo;
import com.lding.pmbok.pojo.vo.resp.DictTypeVo;
import com.lding.pmbok.service.DictTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DictTypeServiceImpl extends ServiceImpl<DictTypeMapper, DictType> implements DictTypeService {
    @Override
    @Transactional(readOnly = true)
    public PageVo<DictTypeVo> getTargetList(DictTypePageReqVo query) {
        // 查询条件
        MyLambdaQueryWrapper<DictType> wrapper = new MyLambdaQueryWrapper<>();

        // 根据关键字查询
        wrapper.like(query.getKeyword(),
                DictType::getName,
                DictType::getValue,
                DictType::getIntro);

        // 按照id降序
        wrapper.orderByDesc(DictType::getId);

        // 分页查询
        return baseMapper
                .selectPage(new MyPage<>(query), wrapper)
                .buildVo(MapStructs.INSTANCE::po2vo);
    }
}
