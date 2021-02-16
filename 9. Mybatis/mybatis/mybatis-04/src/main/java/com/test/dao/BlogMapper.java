package com.test.dao;

import com.test.pojo.Blog;

import java.util.List;
import java.util.Map;

public interface BlogMapper {
    int addBlog(Blog blog);

    List<Blog> queryBlog(Map map);

    List<Blog> queryBlogChoose(Map map);

    int updateBlog(Map map);
}
