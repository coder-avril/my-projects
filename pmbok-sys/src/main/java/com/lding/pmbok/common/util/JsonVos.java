package com.lding.pmbok.common.util;

import com.lding.pmbok.common.exception.CommonException;
import com.lding.pmbok.common.message.CodeMsg;
import com.lding.pmbok.pojo.vo.DataJsonVo;
import com.lding.pmbok.pojo.vo.JsonVo;
import com.lding.pmbok.pojo.vo.PageVo;
import com.lding.pmbok.pojo.vo.ViewJsonVo;

public class JsonVos {
    public static JsonVo error(String msg) {
        return new JsonVo(false, msg);
    }

    public static JsonVo error(CodeMsg msg) {
        return new JsonVo(msg);
    }

    public static JsonVo error(int code, String msg) {
        return new JsonVo(code, msg);
    }

    public static JsonVo error() {
        return new JsonVo(false);
    }

    public static JsonVo ok(CodeMsg msg) {
        return new JsonVo(msg);
    }

    public static JsonVo ok(String msg) {
        return new JsonVo(true, msg);
    }

    public static <T> DataJsonVo<T> ok(T data) {
        return new DataJsonVo<>(data);
    }

    public static <T> ViewJsonVo<T> ok(PageVo<T> pageVo) {
        ViewJsonVo<T> viewJsonVo = new ViewJsonVo<>();
        viewJsonVo.setCount(pageVo.getCount());
        viewJsonVo.setData(pageVo.getData());
        return viewJsonVo;
    }

    public static JsonVo ok() {
        return new JsonVo();
    }

    public static <T> T raise(String msg) throws CommonException {
        throw new CommonException(msg);
    }

    public static <T> T raise(CodeMsg codeMsg) throws CommonException {
        throw new CommonException(codeMsg);
    }
}
