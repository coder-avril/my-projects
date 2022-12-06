package com.lding.pmbok.controller;

import com.lding.pmbok.filter.ErrorFilter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

@RestController
public class ErrorController { // 异常处理Controller（主要接受来自过滤器链来的异常）后面会被CommonExceptionHandler统一处理
    @RequestMapping(ErrorFilter.ERROR_URI)
    public void handle(HttpServletRequest request) throws Exception {
        // 抛出异常
        throw (Exception) request.getAttribute(ErrorFilter.ERROR_URI);
    }
}
