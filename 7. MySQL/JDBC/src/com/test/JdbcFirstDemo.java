package com.test;

import java.sql.*;

public class JdbcFirstDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1. add driver
        Class.forName("com.mysql.jdbc.Driver");  // 加載驅動

        // 2. 寫好曬user info, url
        String url = "jdbc:mysql://localhost:3306/jdbcstudy?useUnicode=true&characterEncoding=utf8&useSSL=true";
        String username = "root";
        String password = "123456";

        // 3. 用username, pwd, url 連接 database
        // 呢個return 一個 connection object，代表數據庫
        Connection connection = DriverManager.getConnection(url,username,password);

        // 4. 用connection object 攞SQL object
        // SQL object 係執行sql 嘅object，寫sql 就用呢個object
        Statement statement = connection.createStatement();


        // 5. 用SQL object (statement) 去執行sql
        String sql = "SELECT * From users";

        ResultSet resultSet = statement.executeQuery(sql); // return 所有result

        while(resultSet.next()){
            System.out.println("id=" + resultSet.getObject("id"));  // 留意 getObject()一定要對應翻 col 名，否則搵唔到
            System.out.println("name=" + resultSet.getObject("NAME"));
            System.out.println("pwd=" + resultSet.getObject("PASSWORD"));
            System.out.println("email=" + resultSet.getObject("email"));
            System.out.println("birth=" + resultSet.getObject("birthday"));
            System.out.println();
        }
    }
}
