package com.fengbin.crowd.test;

import com.fengbin.crowd.entity.Admin;
import com.fengbin.crowd.mapper.AdminMapper;
import com.fengbin.crowd.service.api.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.List;

/*
@作者 冯彬
@时间 2020-06-02-16:34
*/
@RunWith(SpringJUnit4ClassRunner.class)//Spring整合junit
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml"})
public class CrowdTest {

//    @Autowired
//    private DataSource dataSource;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminService adminService;


    @Test
    public void test01() throws SQLException {
            adminMapper.insert(new Admin(null,"zyx","890429zyx","zhangyanxin","zyx@qq.com",null));
    }

    @Test
    public void test02(){
        Logger logger = LoggerFactory.getLogger(CrowdTest.class);
        logger.debug("hello debug");
        logger.info("hello info");
        logger.warn("warn");
        logger.error("error");

    }

    @Test
    public void test03(){ List<Admin> all = adminService.getAll();
        System.out.println(all);
    }

}
