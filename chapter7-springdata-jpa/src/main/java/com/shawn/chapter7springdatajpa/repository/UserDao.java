package com.shawn.chapter7springdatajpa.repository;

import com.shawn.chapter7springdatajpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

//继承JpaRepository来完成对数据库的操作  接口泛型<实体类对象，主键类型>
public interface UserDao extends JpaRepository<User,Integer> {

}
