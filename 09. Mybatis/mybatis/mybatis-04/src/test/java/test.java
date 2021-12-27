import com.test.dao.BlogMapper;
import com.test.pojo.Blog;
import com.test.utils.IDutils;
import com.test.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class test {
    @Test
    public void addBlogTest() {
        SqlSession sqlSession = MybatisUtils.getSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        Blog blog = new Blog();
        blog.setId(IDutils.getId());
        blog.setTitle("Mybatis");
        blog.setAuthor("me");
        blog.setCreateTime(new Date());
        blog.setViews(9999);

        mapper.addBlog(blog);

        blog.setId(IDutils.getId());
        blog.setTitle("Java");
        mapper.addBlog(blog);

        blog.setId(IDutils.getId());
        blog.setTitle("Spring");
        mapper.addBlog(blog);

        blog.setId(IDutils.getId());
        blog.setTitle("MVC");
        mapper.addBlog(blog);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test02(){
        SqlSession sqlSession = MybatisUtils.getSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        Map<Object, Object> map = new HashMap<>();
        map.put("title", "Java");
        mapper.queryBlog(map);

        sqlSession.close();
    }

    @Test
    public void test03(){
        SqlSession sqlSession = MybatisUtils.getSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        HashMap<Object, Object> map = new HashMap<>();
        map.put("views", 9999);
        mapper.queryBlogChoose(map);

        sqlSession.close();
    }

    @Test
    public void test04(){
        SqlSession sqlSession = MybatisUtils.getSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        HashMap<Object, Object> map = new HashMap<>();
        map.put("title", "Mybatis");
        map.put("id", "d92fc636aa1142b9ba347caf873d5f53");
        mapper.updateBlog(map);

        sqlSession.commit();
        sqlSession.close();
    }
}
