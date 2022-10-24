package main.services;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import main.dao.auth.AuthDao;
import main.models.UserDatabase;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    AuthDao authDao;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        LOG.warn("UserDetailsService -> LoadUserByUsername");
        
        //?Obtenemos el Usuario de la Base de datos
        UserDatabase dataBaseUser = authDao.findUserByEmail(email);

        if(dataBaseUser == null) throw new UsernameNotFoundException("User not found");


        LOG.warn(Arrays.toString(dataBaseUser.getRoles().split(",")));

        //? Creamos un Usuario valido para Spring Security

        UserDetails user = User.builder()
            .username(dataBaseUser.getEmail())
            .password(dataBaseUser.getPassword())
            .roles(dataBaseUser.getRoles().split(","))
        .build();

        return user;
    }

}