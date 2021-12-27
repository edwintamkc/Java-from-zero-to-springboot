package com.test.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class CookiesDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        PrintWriter out = resp.getWriter();
        Cookie[] cookies = req.getCookies(); // get cookie array
        if(cookies != null){ // 如果佢有cookies
            boolean hasCookie = false;
            for(int i = 0; i < cookies.length; i++){
                Cookie cookie = cookies[i];
                if(cookie.getName().equals("lastLoginTime")){ //而其中一個係我send嘅 (lastLoginTime)
                    hasCookie = true;
                    long lastLoginTime = Long.parseLong(cookie.getValue()); // 用long獲取
                    Date date = new Date(lastLoginTime); // 轉為日期
                    out.write("Your last login time is: " + date.toString()); // sd response
                }
            }
            if(hasCookie == false) { // 無cookies，第一次進入本站
                out.write("Nice to meet you! Hope to see u again");
            }
        }

        // 無論第一次進入本站與否，重新sd一個時間嘅cookie，用以新建/刷新上次登入時間
        Cookie cookie = new Cookie("lastLoginTime", System.currentTimeMillis() + "");
        resp.addCookie(cookie); // 發送cookie
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
