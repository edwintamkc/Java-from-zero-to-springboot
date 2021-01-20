package com.test.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 獲取文件absolute path
        String abPath = "D:\\CS\\Git\\JavaPractice\\8. Java web\\servlet\\servlet-03\\src\\main\\resources\\1.jpg";
        // 2. 獲取文件名 (for download)
        String filename = "1.jpg";
        // 3. 設置瀏覽器，令佢可以支持下載呢個文件
        resp.setHeader("Content-Disposition", "attachment;filename=" + filename);
        // 4. 獲取inputstream
        FileInputStream in = new FileInputStream(abPath);
        // 5. 獲取outstream (留意係用resp攞outputstream)
        ServletOutputStream out = resp.getOutputStream();
        // 6. buffer
        int len = 0;
        byte[] buffer = new byte[1024];
        // 7. 將FileoutputStream (ServletOutputStream) 寫入buffer，然後輸出
        while((len = in.read(buffer)) != -1){
            out.write(buffer,0,len);
        }
        // 8. close
        in.close();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
