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
        List<User> userList = mapper.getUserList();

        for(User user : userList){
            System.out.println(user);
        }
        sqlSession.close();
    }

    @Test
    public void test2(){
        SqlSession sqlSession = MybatisUtils.getSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getUserById(1);
        System.out.println(user);


        sqlSession.close();
    }

    @Test
    public void test3(){
        SqlSession sqlSession = MybatisUtils.getSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        int result = mapper.addUser(new User(5, "Noname", "23154564"));
        if(result > 0){
            System.out.println("OK!");
        }

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test4(){
        SqlSession sqlSession = MybatisUtils.getSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.updateUser(new User(5,"new name","asdasdzx123"));

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test5(){
        SqlSession sqlSession = MybatisUtils.getSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.deleteUser(5);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test6(){
        SqlSession sqlSession = MybatisUtils.getSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        Map<String, Object> map = new HashMap<>();
        map.put("id", 4);
        map.put("name", "noname");
        mapper.insertUserByMap(map);

        sqlSession.commit();
        sqlSession.close();
    }
}
