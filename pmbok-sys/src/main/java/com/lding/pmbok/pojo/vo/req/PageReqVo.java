package com.lding.pmbok.pojo.vo.req;

import com.lding.pmbok.common.util.Constants;
import lombok.Data;

@Data
public class PageReqVo {
    // 页码
    private long page;

    // 每页的大小
    private long size;

    public long getPage() {
        return Math.max(page, Constants.DEFAULT_MIN_SIZE);
    }

    public long getSize() {
        return size < Constants.DEFAULT_MIN_SIZE ? Constants.DEFAULT_SIZE : size;
    }
}
