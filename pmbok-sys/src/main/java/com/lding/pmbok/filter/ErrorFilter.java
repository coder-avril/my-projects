package com.lding.pmbok.filter;

import org.springframework.stereotype.Component;
import javax.servlet.*;
import java.io.IOException;

/**
 * 自定义具体的过滤器（需要实现javax.servlet.Filter接口
 */
@Component
public class ErrorFilter implements Filter {
    public static final String ERROR_URI = "/handleError";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        } catch (Exception e) { // 过滤器链出问题后，转发到异常处理的controller 统一处理
            request.setAttribute(ERROR_URI, e);
            request.getRequestDispatcher(ERROR_URI).forward(request, response);
        }
    }
}
