package com.example.inteceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object username = request.getSession().getAttribute("username");
        // login failed
        if(username == null){
            request.setAttribute("msg", "Please log in");
            request.getRequestDispatcher("/index.html").forward(request, response);
            return false;
        } else { // success
            return true;
        }
    }
}
