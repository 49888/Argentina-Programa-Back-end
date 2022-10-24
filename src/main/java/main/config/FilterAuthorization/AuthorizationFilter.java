package main.config.FilterAuthorization;


import java.io.IOException;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


import main.models.JwtSubject;
import main.services.JwtService;
import main.services.JwtUserDetailsService;
import main.utils.GsonUtil;


public class AuthorizationFilter extends BasicAuthenticationFilter {
    

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());


    JwtService jwtService;


    JwtUserDetailsService jwtUserDetailsService;

    
    public AuthorizationFilter(AuthenticationManager authenticationManager, JwtService jwtService, JwtUserDetailsService jwtUserDetailsService){
        
        super(authenticationManager);

        this.jwtService = jwtService;
        
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        LOG.warn("-- AUTENTICANDO EL TOKEN --");

        UsernamePasswordAuthenticationToken auth = getAuthentication(request);

        if(auth == null){

            chain.doFilter(request, response);

            LOG.warn("-- Invalid Token --");
        }
        else{

            LOG.warn("-- Valid Token --");

            SecurityContextHolder.getContext().setAuthentication(auth);
            
            chain.doFilter(request, response);
        }

    }


    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request){

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(token == null || !token.startsWith("Bearer ")) return null;

        String jwt = token.replace("Bearer ", "");

        boolean valid = jwtService.validateToken(jwt);

        if(!valid) return null;

        JwtSubject subject = GsonUtil.toObject(jwtService.getSubject(jwt), JwtSubject.class);


        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(subject.getClientId());

        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
    }
}
