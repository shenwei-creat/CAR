package com.example.demo.Config;



import com.example.demo.Controller.UserController;
import com.example.demo.MD5Util;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    //授权
    protected void configure(HttpSecurity http) throws Exception {
        //首页所有人能访问功能页只有有权限的人能访问
        http.authorizeRequests().
                antMatchers("/").permitAll().
                antMatchers("/article").hasRole("simple").
                antMatchers("/article-edit").hasRole("simple").
                antMatchers("/home-article").hasRole("simple").
                antMatchers("/user-self-edit").hasRole("simple").
                antMatchers("/home-article").hasRole("simple").
                antMatchers("/user-edit").hasRole("super").
                antMatchers("/update/*  ").hasRole("super").
                antMatchers("/user").hasRole("super");
        http.formLogin().loginPage("/tologin");
        http.csrf().disable();//关闭csrf功能
        http.logout().logoutSuccessUrl("/");
        http.rememberMe().rememberMeParameter("remember");

    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).
                withUser("root@123").password(new BCryptPasswordEncoder().encode("123456")).roles("super","simple").
                and().
                withUser("admin@123").password(new BCryptPasswordEncoder().encode("123456")).roles("simple");

    }


}
