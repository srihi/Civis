package com.smarty.civis.sync;

import com.squareup.moshi.Json;

/**
 * Created by anh.hoang on 6/27/17.
 */

public class TokenResponse {
    @Json(name = "token_type")
    private String tokenType;
    @Json(name = "expires_in")
    private long expiresIn;
    @Json(name = "access_token")
    private String accessToken;
    @Json(name = "refresh_token")
    private String refreshToken;

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
