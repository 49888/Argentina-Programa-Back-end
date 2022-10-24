package main.models;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class JwtSubject {
    
    private String username;

    private String clientId;
}
