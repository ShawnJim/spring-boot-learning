package com.shawn.chapter7springdatajpa;

import com.shawn.chapter7springdatajpa.entity.User;
import com.shawn.chapter7springdatajpa.repository.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter7SpringdataJpaApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(Chapter7SpringdataJpaApplicationTests.class);

    @Autowired
    UserDao userDao;

    @Test
    public void contextLoads() {
        User user = new User("user1", "2510xxxxxxxx.@xx.com");
        User flush = userDao.saveAndFlush(user);
        log.info(String.format("插入用户数据 用户id: {%s}",user.getId()));
        Optional<User> userById = userDao.findById(user.getId());
        log.info(String.format("查询用户 user: {%s}",userById.toString()));
    }

}
