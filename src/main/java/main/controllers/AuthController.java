package main.controllers;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import main.dao.auth.AuthDao;
import main.models.UserDatabase;

@RestController @RequestMapping("/api/auth")
public class AuthController {

    private Logger LOG = LoggerFactory.getLogger(this.getClass());
    
    @RequestMapping("/user")
    public String getUser(){

        return "Eres User";
    }

    @RequestMapping("/admin")
    public String getAdmin(){

        return "Eres Admin";
    }

    
    @RequestMapping("/info")
    public Object getInfo(Principal principal){

        return principal;
    }

    @Autowired
    AuthDao authDao;

    @RequestMapping("/test")
    public UserDatabase test(@RequestParam("client-id") String clientId){

        LOG.warn(clientId);

        UserDatabase user = authDao.findUserByEmail(clientId);

        LOG.warn(user.toString());

        return user;
    }
}
