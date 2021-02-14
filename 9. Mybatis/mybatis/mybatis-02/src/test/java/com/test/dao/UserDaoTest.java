package com.test.dao;

import com.test.pojo.User;
import com.test.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoTest {
    @Test
    public void test(){
        // 獲取sqlSession object
        SqlSession sqlSession = MybatisUtils.getSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getUserById(1);


        System.out.println(user);

        sqlSession.close();
    }
}
