package com.test.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloController implements Controller{
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView();

        /*加object，放入 modelAndView中*/
        mv.addObject("msg","HelloSpringMVC!");
        /*設置要轉發嘅view， hello 。由於已經配好resolver，會自動加prefix，suffix*/
        mv.setViewName("hello"); // /WEB-INF/jsp/hello.jsp
        return mv;
    }
}
