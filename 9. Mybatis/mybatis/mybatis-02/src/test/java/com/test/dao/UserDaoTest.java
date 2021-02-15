package com.test.dao;

import com.test.pojo.User;
import com.test.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserDaoTest {

    static Logger logger = Logger.getLogger(UserDaoTest.class);

    @Test
    public void test(){
        // 獲取sqlSession object
        SqlSession sqlSession = MybatisUtils.getSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getUserById(1);
        System.out.println(user);

        logger.info("info: hello!!!!!!!!!!!");

        sqlSession.close();
    }

    @Test
    public void test2(){
        SqlSession sqlSession = MybatisUtils.getSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        Map<String, Integer> map = new HashMap<>();
        map.put("startIndex",0);
        map.put("pageSize", 2);
        List<User> users = mapper.getUserByLimit(map);
        for (User user : users) {
            System.out.println(user);
        }


        sqlSession.close();
    }
}
