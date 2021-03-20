package com.example.demo.mapper;

import com.example.demo.dao.User;
import com.example.demo.entity.Resp;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    List<User> queryUserList();
    User queryUserById(int id);
    int addUser(User user);
    int updateUser(User user);
    int deleteUser(int id);
    User queryUserByEmail(String email);
    User userlogin(@Param("username") String username,@Param("password") String password);
    int adduser(@Param("username") String username, @Param("password") String password,@Param("name") String name);
    Resp<String> upload(MultipartFile file);
}
