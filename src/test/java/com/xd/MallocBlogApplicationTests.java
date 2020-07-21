package com.xd;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.entity.TBlog;
import com.xd.mapper.TBlogMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
class MallocBlogApplicationTests {

    @Autowired
    TBlogMapper blogMapper;

    @Test
    void contextLoads() {
        Page<TBlog> page =new Page<>(1,1);
        blogMapper.selectPage(page,null);
        System.out.println(page.getPages());
        page.getRecords().forEach(System.out::println);
    }

}
