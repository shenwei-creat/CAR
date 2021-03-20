package com.example.demo.Controller;

import com.example.demo.dao.User;
import com.example.demo.entity.Resp;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class UserController {
    @Autowired
     private UserService userService;

    @GetMapping     ("/user")
    public String userList(Model model){
        List<User> user= userService.queryUserList();
        model.addAttribute("user",user);

        return "user";
    }
    @PostMapping("/users")
    public String addUser(User user){

        userService.addUser(user);
        return "redirect:/user";
    }
    @GetMapping("/updateUser")
    public String updateUser(User user){
        userService.updateUser(user);
        return "redirect:/user";
    }
    @GetMapping("/update/{id}")
    public String toupdate(@PathVariable("id") Integer id,Model model){
        User user=userService.queryUserById(id);
        model.addAttribute("user",user);
        return "update";

    }
    @GetMapping("/deleteUser/{id}")
    public String delete(@PathVariable Integer id){
        userService.deleteUser(id);
        return "redirect:/user";
    }
    @RequestMapping("/tologin")
    public String userLogin(@RequestParam(value = "username", required = false) String email, @RequestParam(value = "password", required = false) String password, HttpServletRequest request){

        User user = userService.userLogin(email,password);
        if(user != null){                                                  //登录成功
            request.getSession().setAttribute("session_user",user);

            return "article";
        }
        System.out.println("a");
        return "login";
    }
    @RequestMapping("/toregister")
    public String addUser(@RequestParam(value = "username", required = false) String email,@RequestParam(value = "password", required = false)String password
    ,@RequestParam(value = "name", required = false)String name){

         int res =userService.adduser(email,password,name);
        if(res == 0){
            System.out.println("a");
            return "register";
        }else {
            System.out.println("b");
            return "login";
        }
    }
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    private Resp<String> upload(@RequestParam("file")MultipartFile file){
        return userService.upload(file);
    }


    }



