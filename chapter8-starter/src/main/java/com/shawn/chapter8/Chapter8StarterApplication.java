package com.shawn.chapter8;

import com.shawn.mystarter.mystarter.bean.User;
import com.shawn.mystarter.mystarter.util.SpringUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class Chapter8StarterApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Chapter8StarterApplication.class, args);
        System.out.println(SpringUtil.getBean(User.class).toString());
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        //目的
        return args -> {
            System.out.println("看看 SpringBoot 默认为我们提供的bean");
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            Arrays.stream(beanNames).forEach(System.out::println);
        };
    }
}
