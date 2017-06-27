package com.smarty.civis.sync;

import com.squareup.moshi.Json;

/**
 * Created by anh.hoang on 6/27/17.
 */

class TokenRequest {
    @Json(name = "grand_type")
    private final String grandType;
    @Json(name = "client_id")
    private final String clientId;
    @Json(name = "client_secret")
    private final String clientSecret;
    @Json(name = "redirect_uri")
    private final String redirectUri;
    @Json(name = "code")
    private final String authorizationCode;

    public TokenRequest(String grandType, String clientId, String clientSecret, String redirectUri, String authorizationCode){

        this.grandType = grandType;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
        this.authorizationCode = authorizationCode;
    }
}
