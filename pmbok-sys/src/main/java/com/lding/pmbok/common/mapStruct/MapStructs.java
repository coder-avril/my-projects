package com.lding.pmbok.common.mapStruct;

import com.lding.pmbok.pojo.po.*;
import com.lding.pmbok.pojo.vo.LoginVo;
import com.lding.pmbok.pojo.vo.req.save.*;
import com.lding.pmbok.pojo.vo.resp.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * ReqVo -> Po
 * Po -> Vo
 */
@Mapper(uses = {MapStructFormatter.class})
public interface MapStructs {
    MapStructs INSTANCE = Mappers.getMapper(MapStructs.class);

    DictTypeVo po2vo(DictType po);
    DictItemVo po2vo(DictItem po);
    @Mapping(source = "loginTime", target = "loginTime", qualifiedBy = MapStructFormatter.Date2Millis.class)
    SysUserVo po2vo(SysUser po);
    SysRoleVo po2vo(SysRole po);
    SysResourceVo po2vo(SysResource po);
    LoginVo po2loginVo(SysUser po);

    DictType reqSaveVo2po(DictTypeReqSaveVo reqSaveVo);
    DictItem reqSaveVo2po(DictItemReqSaveVo reqSaveVo);
    SysUser reqSaveVo2po(SysUserReqSaveVo reqSaveVo);
    SysRole reqSaveVo2po(SysRoleReqSaveVo reqSaveVo);
    SysResource reqSaveVo2po(SysResourceReqSaveVo reqSaveVo);
}
