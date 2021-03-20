package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouterController {
    @RequestMapping({"/","/default"})
    public String index(){
        return "default";
    }
    @RequestMapping("/article")
    public String article(){
        return "article";
    }
    @RequestMapping("/article-edit")
    public String articleedit(){
        return "article-edit";
    }
    @RequestMapping("/update")
    public String update(){
        return "update";
    }
    @RequestMapping("/register")
    public String register(){ return "register"; }
    @RequestMapping("/user-edit")
    public String useredit(){
        return "user-edit";
    }
    @RequestMapping("/user-self-edit")
    public String userselfedit(){
        return "user-self-edit";
    }
    @RequestMapping("/home-article")
    public String homearticle(){
        return "home-article";
    }
    @RequestMapping("/upload")
    public String Upload(){
        return "upload";
    }

}
