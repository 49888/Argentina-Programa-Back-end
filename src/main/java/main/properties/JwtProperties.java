package main.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.jwt")
public class JwtProperties {

    private String timezone;

    private String issuer;

    private Token token;

    private String excluded;

    @Data
    public static class Token {

        private String auth_path;

        private String secret;

        private int expiresIn;
    }
}
