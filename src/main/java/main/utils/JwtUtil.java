package main.utils;

import java.time.ZonedDateTime;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.fusionauth.jwt.Signer;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACSigner;
import io.fusionauth.jwt.hmac.HMACVerifier;

@Component
public class JwtUtil {

    @Value("${app.jwt.issuer:none}")
    private String ISSUER;

    @Value("${app.jwt.token.secret:secret}")
    private String SECRET;

    @Value("${app.jwt.token.expires-in:3600}")
    private String EXPIRES_IN;

    @Value("${app.jwt.timezone:UTC}")
    private String TIMEZONE;
    



    public String generateToken(String subject){



        //! Contruir un HMAC Signer usando SHA-256
        Signer signer = HMACSigner.newSHA256Signer(SECRET);

        TimeZone tz = TimeZone.getTimeZone(TIMEZONE);

        ZonedDateTime zdt = ZonedDateTime.now(tz.toZoneId());

        JWT jwt = new JWT()
            .setIssuer(ISSUER)
            .setIssuedAt(zdt)
            .setSubject(subject)
        .setExpiration( zdt.plusSeconds(Long.parseLong(EXPIRES_IN)) );


        return JWT.getEncoder().encode(jwt, signer);
    }


    public boolean validateToken(String encodedJWT){

        boolean valid = false;

        try {
            
            JWT jwt = getJwt(encodedJWT);

            valid = !jwt.isExpired();
        }
        catch (Exception e) {}


        return valid;
    }

    public String getSubject(String encodedJWT){

        String subject = null;
        
        try {
            
            JWT jwt = getJwt(encodedJWT);

            subject = jwt.subject;
        }
        catch (Exception e) {}
        
        return subject;
    }
    
    
    private JWT getJwt(String encodedJWT){
         
        Verifier verifier = HMACVerifier.newVerifier(SECRET);

        return JWT.getDecoder().decode(encodedJWT, verifier);
    }
}
