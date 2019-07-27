package com.shawn.chapter6.dao;

import com.shawn.chapter6.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
//或者在application类中使用@MapperScan 注解进行统一管理
public interface UserMapper {

    @Select("select * from t_user where id=#{id}")
    public User getById(Integer id);

    @Delete("delete from t_user where id=#{id}")
    public int deleteById(Integer id);


    @Options(useGeneratedKeys = true,keyProperty = "id")//主键返回
    @Insert("insert into t_user(username,password) values(#{username},#{password})")
    public int insert(User user);

    @Update("update t_user set username=#{username} where id=#{id}")
    public int update(User user);

    public int insertOne(User user);
}
