package com.shawn.chapter5;

import com.shawn.chapter5.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter5JdbcApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(Chapter5JdbcApplicationTests.class);

    @Autowired
    DataSource dataSource;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testInsert() throws Exception {
        testRestTemplate.postForEntity("http://localhost:" + 8080 + "/users", new User("user1", "pass1"), Integer.class);
        log.info("[添加用户成功]\n");
        // TODO 如果是返回的集合,要用 exchange 而不是 getForEntity ，后者需要自己强转类型
        final List<User> body = testRestTemplate.exchange("http://localhost:" + 8080 + "/users", HttpMethod.GET
                , null, new ParameterizedTypeReference<List<User>>(){}).getBody();
        log.info("[查询所有] - [{}]\n", body);
        int userId = body.get(0).getId();
        ResponseEntity<User> response3 = testRestTemplate.getForEntity("http://localhost:" + 8080 + "/users/{id}", User.class, userId);
        log.info("[主键查询] - [{}]\n", response3.getBody());
        testRestTemplate.put("http://localhost:" + 8080 + "/users/{id}", new User("user11", "pass11"), userId);
        log.info("[修改用户成功]\n");
        testRestTemplate.delete("http://localhost:" + 8080 + "/users/{id}", userId);
        log.info("[删除用户成功]");
    }

    @Test
    public void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());

        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }

}
