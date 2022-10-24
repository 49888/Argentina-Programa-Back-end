package main.services;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import main.models.AccessTokenResponse;
import main.utils.GsonUtil;
import main.utils.JwtUtil;

@Service
public class JwtService {

    private Logger LOG = LoggerFactory.getLogger(this.getClass());
    

    @Value("${app.jwt.token.expires-in}")
    private String expiresIn;

    @Value("${app.jwt.issuer}")
    private String issuer;


    @Autowired
    JwtUtil jwt;


    public AccessTokenResponse generateToken(String clientId, Object subject){

        LOG.warn("GENERATE TOKEN");

        String token = jwt.generateToken(GsonUtil.serialize(subject));

        AccessTokenResponse response = AccessTokenResponse.builder()
            .accessToken(token)
            .tokenType("Bearer")
            .clientId(clientId)
            .issuer(issuer)
            .issuedAt( Long.toString(System.currentTimeMillis()) )
            .expiresIn(expiresIn)
        .build();

        return response;
    }

    public boolean validateToken(String encodedJwt){

        LOG.warn("VALIDATE TOKEN");

        return jwt.validateToken(encodedJwt);
    }


    public String getSubject(String encodedJwt){

        return jwt.getSubject(encodedJwt);
    }
}
