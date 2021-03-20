package com.example.demo.service;

import com.example.demo.dao.User;

import com.example.demo.entity.Resp;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Collection;
import java.util.List;

@Service
public class UserService {


    @Autowired
    private UserMapper userMapper;
    public User queryUserById(int id){
        return userMapper.queryUserById(id);
    }
    public  List<User> queryUserList(){
        return userMapper.queryUserList();
    }
    public void addUser(User user){
        userMapper.addUser(user);
    }
    public int deleteUser(Integer id){
        return userMapper.deleteUser(id);
    }
    public int updateUser(User user){
        return userMapper.updateUser(user);
    }
    public User queryUserByEmail(String email){
        return userMapper.queryUserByEmail(email);
    }
    public  User userLogin(String email, String password){ return userMapper.userlogin(email,password); }
    public int adduser(String username,String password,String name){ return userMapper.adduser(username,password,name); }
    public Resp<String> upload(MultipartFile file){
        if(file.isEmpty()){
            return Resp.fail("400","文件为空");
        }
        String OriginalFilename = file.getOriginalFilename();
        String fileName = System.currentTimeMillis()+"."+
                OriginalFilename.substring(OriginalFilename.lastIndexOf(".")+1);
        String filepath = "F:\\上传文件\\";//储存文件地址
        File dest = new File(filepath+fileName);
        //判断filepath是否存在
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        }catch (Exception e){
            e.printStackTrace();
            return Resp.fail("500",OriginalFilename+"上传失败");
        }
        return Resp.success(fileName);
    }
}
