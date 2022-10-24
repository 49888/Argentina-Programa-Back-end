package main.config.FilterAuthentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import main.models.AccessTokenResponse;
import main.models.JwtSubject;
import main.services.JwtService;
import main.utils.GsonUtil;

@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());


    @Autowired
    JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        
        LOG.warn("-- Authentication Success Handler --");

        UserDetails principal = (UserDetails) authentication.getPrincipal();

        LOG.warn(principal.getUsername());

        LOG.warn(principal.getPassword());


        //! Generar el Token JWT
        JwtSubject subject = JwtSubject.builder()
            .clientId(principal.getUsername())
        .build();

        AccessTokenResponse tokenResponse = jwtService.generateToken(principal.getUsername(), subject);

        //? Devolver El Token
        response.addHeader("Authorization", "Bearer " + tokenResponse.getAccessToken());

        response.addHeader("Content-Type", "application/json");

        response.getWriter().write(GsonUtil.serialize(tokenResponse));
    }
    
}
