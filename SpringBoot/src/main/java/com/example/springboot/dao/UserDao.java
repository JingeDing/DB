package com.example.springboot.dao;

import com.example.springboot.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {
    public User findByUsername(String username);

    public void addUser(User user);

    @Select("Select * from user where username=#{username} AND password=#{password} ")
    public User getInfo(@Param("username") String username, @Param("password") String password);

    public void updatePassword(@Param("username") String username, @Param("password") String password);
}
