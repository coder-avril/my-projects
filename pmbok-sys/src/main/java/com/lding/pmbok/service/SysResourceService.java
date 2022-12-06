package com.lding.pmbok.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lding.pmbok.pojo.po.SysResource;
import com.lding.pmbok.pojo.vo.PageVo;
import com.lding.pmbok.pojo.vo.req.select.SysResourcePageReqVo;
import com.lding.pmbok.pojo.vo.resp.SysResourceTreeVo;
import com.lding.pmbok.pojo.vo.resp.SysResourceVo;
import java.util.List;

public interface SysResourceService extends IService<SysResource> {
    PageVo<SysResourceVo> getTargetList(SysResourcePageReqVo reqVo);

    List<SysResourceVo> listParents();

    List<SysResourceTreeVo> listTree();

    List<Integer> listIds(Integer roleId);

    List<SysResource> listByRoleIds(List<Integer> roleIds);
}

