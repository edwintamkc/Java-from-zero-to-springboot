package com.test;

import javax.servlet.*;
import java.io.IOException;

public class CharacterEncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 1. 將request, response, contenttype 亂碼問題解決
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setCharacterEncoding("utf-8");
        servletResponse.setContentType("text/html;charset=utf-8");

        // 2. 用filter chain將信號再傳出去
        filterChain.doFilter(servletRequest,servletResponse);

        // 3. set web.xml 映射
    }

    @Override
    public void destroy() {

    }
}
