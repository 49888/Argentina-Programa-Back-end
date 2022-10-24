package main.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class AccessTokenResponse {
    
    @JsonProperty(value = "access_token")
    @SerializedName("access_token")
    private String accessToken;

    @JsonProperty(value = "token_type")
    @SerializedName(value = "token_type")
    private String tokenType;

    @JsonProperty(value = "expires_in")
    @SerializedName(value = "expires_in")
    private String expiresIn;

    @JsonProperty(value = "issued_at")
    @SerializedName(value = "issued_at")
    private String issuedAt;

    @JsonProperty(value = "issuer")
    @SerializedName(value = "issuer")
    private String issuer;

    @JsonProperty(value = "client_id")
    @SerializedName(value = "client_id")
    private String clientId;
}
