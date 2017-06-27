package com.smarty.civis.sync;

/**
 * Created by anh.hoang on 6/27/17.
 */

class TokenRefreshRequest {
    private final String grantType;
    private final String clientId;
    private final String clientSecret;
    private final String refreshToken;
    private final String scope;

    public TokenRefreshRequest(String grantType, String clientId, String clientSecret, String refreshToken, String scope){
        this.grantType = grantType;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.refreshToken = refreshToken;
        this.scope = scope;
    }
}
