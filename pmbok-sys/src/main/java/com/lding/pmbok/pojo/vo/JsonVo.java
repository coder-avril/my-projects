package com.lding.pmbok.pojo.vo;

import com.lding.pmbok.common.message.CodeMsg;
import lombok.Data;

/**
 * 面向客户端的普通Json格式Vo
 *  一般用于返回“成功”或“失败”的信息（多见于save、remove之类的方法中
 */
@Data
public class JsonVo {
    private static final int CODE_OK = CodeMsg.OPERATE_OK.getCode();
    private static final int CODE_ERROR = CodeMsg.BAD_REQUEST.getCode();

    // 代码【0代表成功，其他代表失败】
    private Integer code;

    // 消息描述
    private String msg;

    public JsonVo() {
        this(true);
    }

    public JsonVo(boolean ok) {
        this(ok, null);
    }

    public JsonVo(boolean ok, String msg) {
        this(ok ? CODE_OK : CODE_ERROR, msg);
    }

    public JsonVo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public JsonVo(CodeMsg codeMsg) {
        this(codeMsg.getCode(), codeMsg.getMsg());
    }
}
