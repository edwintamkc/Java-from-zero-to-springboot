package com.test.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = this.getServletContext();

        // RequestDispatcher係專門做轉發嘅class
        // 係呢一步先攞到轉發嘅位置 (位置需要係web.xml登記)
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/getc");// 轉發嘅位置
        // 藉助req 及 resp轉發
        requestDispatcher.forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
