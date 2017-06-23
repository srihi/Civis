package com.smarty.civis.activities.utils;

import android.content.Intent;
import android.net.Uri;

import com.smarty.civis.BuildConfig;

/**
 * Created by anh.hoang on 6/24/17.
 */

public class AuthUtils {
    private static final String URL = "https://v1-sso-api.digitaltown.com/oauth/authorize";
    private static final String CLIENT_ID_PARAM = "client_id";

    private static final String RESPONSE_TYPE_PARAM = "response_type";
    private static final String RESPONSE_TYPE = "code";

    private static final String SCOPE_PARAM = "scope";
    private static final String SCOPE = "ID,email,first_name,last_name,avatar,reputation_score";

    private static final String REDIRECT_URI_PARAM = "redirect_uri";
    private static final String REDIRECT_URI = "http://auth/callback";

    private AuthUtils() {
        throw new AssertionError("No Instance for you!");
    }


    public static Intent getAuthenticationIntent() {
        return new Intent(Intent.ACTION_VIEW, getAuthorizationUrl());
    }

    private static Uri getAuthorizationUrl() {
        Uri authUrl = Uri.parse(URL)
                .buildUpon()
                .appendQueryParameter(CLIENT_ID_PARAM, BuildConfig.DT_CLIENT_ID)
                .appendQueryParameter(RESPONSE_TYPE_PARAM, RESPONSE_TYPE)
                .appendQueryParameter(SCOPE_PARAM, SCOPE)
                .appendQueryParameter(REDIRECT_URI_PARAM, REDIRECT_URI)
                .build();

        return authUrl;
    }
}
