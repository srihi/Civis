package com.smarty.civis.utils;

import android.content.Intent;
import android.net.Uri;

import com.smarty.civis.BuildConfig;

/**
 * Created by anh.hoang on 6/24/17.
 */

public class AuthUtils {
    private static final String URL = "https://v1-sso-api.digitaltown.com/";
    private static final String AUTH_URL = URL + "oauth/authorize";
    private static final String REGISTER_URL = URL + "register";
    private static final String CLIENT_ID_PARAM = "client_id";

    private static final String RESPONSE_TYPE_PARAM = "response_type";
    private static final String RESPONSE_TYPE = "code";

    private static final String SCOPE_PARAM = "scope";
    private static final String SCOPE = "email";

    private static final String CALLBACK_PARAM = "callback";
    private static final String REDIRECT_URI_PARAM = "redirect_uri";
    private static final String REDIRECT_URI = "http://auth/callback";

    private AuthUtils() {
        throw new AssertionError("No Instance for you!");
    }


    public static Intent getAuthenticationIntent() {
        return generateIntent(getAuthorizationUrl());
    }

    public static Intent getRegisterIntent() {
        return generateIntent(getRegisterUrl());
    }

    private static Intent generateIntent(Uri uri) {
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }

    private static Uri getRegisterUrl() {
        Uri registerUrl = Uri.parse(REGISTER_URL)
                .buildUpon()
                .appendQueryParameter(CALLBACK_PARAM, REDIRECT_URI)
                .build();

        return registerUrl;
    }

    private static Uri getAuthorizationUrl() {
        Uri authUrl = Uri.parse(AUTH_URL)
                .buildUpon()
                .appendQueryParameter(CLIENT_ID_PARAM, BuildConfig.DT_CLIENT_ID)
                .appendQueryParameter(RESPONSE_TYPE_PARAM, RESPONSE_TYPE)
                .appendQueryParameter(SCOPE_PARAM, SCOPE)
                .appendQueryParameter(REDIRECT_URI_PARAM, REDIRECT_URI)
                .build();

        return authUrl;
    }
}
