package com.lding.pmbok.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lding.pmbok.common.enhance.MyLambdaQueryWrapper;
import com.lding.pmbok.common.enhance.MyPage;
import com.lding.pmbok.common.mapStruct.MapStructs;
import com.lding.pmbok.mapper.DictItemMapper;
import com.lding.pmbok.pojo.po.DictItem;
import com.lding.pmbok.pojo.vo.PageVo;
import com.lding.pmbok.pojo.vo.req.select.DictItemPageReqVo;
import com.lding.pmbok.pojo.vo.resp.DictItemVo;
import com.lding.pmbok.service.DictItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DictItemServiceImpl extends ServiceImpl<DictItemMapper, DictItem> implements DictItemService {
    @Override
    @Transactional(readOnly = true)
    public PageVo<DictItemVo> getTargetList(DictItemPageReqVo query) {
        // 查询条件
        MyLambdaQueryWrapper<DictItem> wrapper = new MyLambdaQueryWrapper<>();
        wrapper.like(query.getKeyword(), DictItem::getName, DictItem::getValue);
        Integer typeId = query.getTypeId();
        if (typeId != null && typeId > 0) {
            wrapper.eq(DictItem::getTypeId, typeId);
        }

        // 排序
        wrapper.orderByDesc(DictItem::getId);

        // 分页查询
        return baseMapper
                .selectPage(new MyPage<>(query), wrapper)
                .buildVo(MapStructs.INSTANCE::po2vo);
    }
}
