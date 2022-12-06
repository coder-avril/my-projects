package com.lding.pmbok.common.enhance;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lding.pmbok.common.util.Streams;
import com.lding.pmbok.pojo.vo.PageVo;
import com.lding.pmbok.pojo.vo.req.PageReqVo;
import java.util.List;
import java.util.function.Function;

public class MyPage<T> extends Page<T> {
    private final PageReqVo reqVo;

    public MyPage(PageReqVo reqVo) {
        super(reqVo.getPage(), reqVo.getSize());
        this.reqVo = reqVo;
    }

    private <N> PageVo<N> commonBuildVo(List<N> data) {
        reqVo.setPage(getCurrent());
        reqVo.setSize(getSize());

        PageVo<N> pageVo = new PageVo<>();
        pageVo.setCount(getTotal());
        pageVo.setPages(getPages());
        pageVo.setData(data);
        return pageVo;
    }

    public PageVo<T> buildVo() {
        return commonBuildVo(getRecords());
    }

    public <R> PageVo<R> buildVo(Function<T, R> function) {
        return commonBuildVo(Streams.map(getRecords(), function));
    }
}
