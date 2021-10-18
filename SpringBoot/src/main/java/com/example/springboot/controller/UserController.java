package com.example.springboot.controller;

import com.example.springboot.pojo.User;
import com.example.springboot.service.UserService;
import com.example.springboot.Response.Response;
import org.apache.jasper.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    @Autowired
    private UserService us;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response login(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        User user1 = us.LoginIn(username, password);
        System.out.println(username);
        System.out.println(password);
        if (user1 != null) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            System.out.println("success");
            return new Response(200, "success", token);
        } else {
            return new Response(500, "failure", null);
        }
    }

    @RequestMapping("updatePassword/{username}/{password}")
    public Response updatePassword(@PathVariable("username") String username,
            @PathVariable("password") String password) {
        try {
            us.updatePassword(username, password);
            return new Response(200, "success", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(500, "failure", null);
        }
    }
}
