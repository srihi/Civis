package com.smarty.civis.sync;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anh.hoang on 6/27/17.
 */

class TokenRequest {
    @SerializedName("grant_type")
    private final String grantType;
    @SerializedName("client_id")
    private final String clientId;
    @SerializedName("client_secret")
    private final String clientSecret;
    @SerializedName("redirect_uri")
    private final String redirectUri;
    @SerializedName("code")
    private final String authorizationCode;

    public TokenRequest(String grantType, String clientId, String clientSecret, String redirectUri, String authorizationCode) {

        this.grantType = grantType;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
        this.authorizationCode = authorizationCode;
    }
}
