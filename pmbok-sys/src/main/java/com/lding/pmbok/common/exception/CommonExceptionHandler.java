package com.lding.pmbok.common.exception;

import com.lding.pmbok.common.message.CodeMsg;
import com.lding.pmbok.common.util.JsonVos;
import com.lding.pmbok.common.util.Streams;
import com.lding.pmbok.pojo.vo.JsonVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;

@RestControllerAdvice
// 该注解相当于@ControllerAdvice+@ResponseBody 可以实现三个方面的功能：全局异常处理、全局数据绑定、全局数据预处理
// 默认行为（即，如果没有任何选择器使用），带@ControllerAdvice注释的类将协助所有已知的控制器
@Slf4j
public class CommonExceptionHandler {
    @ExceptionHandler(Throwable.class) // 可以指定异常类型的范围
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public JsonVo handle(Throwable t) {
        log.error("handle", t);

        // 一些可以直接处理的异常
        if (t instanceof CommonException) {
            return handle((CommonException) t);
        } else if (t instanceof BindException) {
            return handle((BindException) t);
        } else if (t instanceof ConstraintViolationException) {
            return handle((ConstraintViolationException) t);
        } else if (t instanceof AuthorizationException) {
            return JsonVos.error(CodeMsg.NO_PERMISSION);
        }

        // 处理cause异常（导致产生t的异常）
        Throwable cause = t.getCause();
        if (cause != null) {
            return handle(cause);
        }

        // 其他异常（没有cause的异常）
        return JsonVos.error();
    }

    private JsonVo handle(CommonException ce) {
        return JsonVos.error(ce.getCode(), ce.getMessage());
    }

    private JsonVo handle(BindException be) {
        List<ObjectError> errors = be.getBindingResult().getAllErrors();
        // 函数式编程的方式：stream
        List<String> defaultMsgs = Streams.map(errors, ObjectError::getDefaultMessage);
        String msg = StringUtils.collectionToDelimitedString(defaultMsgs, ", ");
        return JsonVos.error(msg);
    }

    private JsonVo handle(ConstraintViolationException cve) {
        List<String> msgs = Streams.map(cve.getConstraintViolations(), ConstraintViolation::getMessage);
        String msg = StringUtils.collectionToDelimitedString(msgs, ", ");
        return JsonVos.error(msg);
    }
}
