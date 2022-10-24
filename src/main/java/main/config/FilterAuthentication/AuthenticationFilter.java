package main.config.FilterAuthentication;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import main.models.UserCredentials;
import main.utils.GsonUtil;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {


        LOG.warn("-- Authentication Filter --");

        try {
         
            //UserCredentials userCredentials = formEncode(request);

            UserCredentials userCredentials = bodyJson(request);
  
            LOG.warn("Credentials: " + userCredentials.toString());

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userCredentials.getEmail(), userCredentials.getPassword());

            this.setDetails(request, token);

            return this.getAuthenticationManager().authenticate(token);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return super.attemptAuthentication(request, response);
    }


    private UserCredentials formEncode(HttpServletRequest request){

        String email = request.getParameter("email");

        String password = request.getParameter("password");

        return new UserCredentials(email, password);
    }

    private UserCredentials bodyJson(HttpServletRequest request){

        
        try {
            
            BufferedReader bufferedReader = request.getReader();

            StringBuilder json = new StringBuilder();

            String line;

            while((line = bufferedReader.readLine()) != null){

                json.append(line);
            }

            
            UserCredentials userCredentials = GsonUtil.toObject(json.toString(), UserCredentials.class);

            
            return userCredentials;

        } catch (Exception e) {
            
        }

        return null;
    }
}
