package com.smarty.civis.sync;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anh.hoang on 6/27/17.
 */

class TokenRefreshRequest {
    @SerializedName("grant_type")
    private final String grantType;
    @SerializedName("client_id")
    private final String clientId;
    @SerializedName("client_secret")
    private final String clientSecret;
    @SerializedName("refresh_token")
    private final String refreshToken;
    @SerializedName("scope")
    private final String scope;

    public TokenRefreshRequest(String grantType, String clientId, String clientSecret, String refreshToken, String scope) {
        this.grantType = grantType;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.refreshToken = refreshToken;
        this.scope = scope;
    }
}
