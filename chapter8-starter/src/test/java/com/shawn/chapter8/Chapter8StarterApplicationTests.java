package com.shawn.chapter8;

import com.shawn.mystarter.mystarter.bean.User;
import com.shawn.mystarter.mystarter.service.UserSevice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter8StarterApplicationTests {

    @Test
    public void contextLoads() {
        User user = UserSevice.getUser();
        System.out.println(user.toString());
    }

}
