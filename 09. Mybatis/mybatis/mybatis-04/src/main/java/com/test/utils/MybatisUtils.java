package com.test.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

// sqlSessionFactory -> sqlSession (用作執行sql，入面有執行SQL嘅method)
public class MybatisUtils {
    private static SqlSessionFactory sqlSessionFactory;

    static{
        // 獲取 sqlSessionFactory object
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 提供方法return一個 sqlSession
    // 呢個sqlSession就係用sqlSessionFactory 產出
    public static SqlSession getSession(){
        return sqlSessionFactory.openSession();
    }
}
