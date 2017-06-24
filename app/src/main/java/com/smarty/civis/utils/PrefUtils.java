package com.smarty.civis.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.smarty.civis.R;

/**
 * Created by anh.hoang on 6/24/17.
 */

public class PrefUtils {
    private static final String CODE_PARAM = "code";

    public static void putToken(Context context, Uri data) {
        String token = data.getQueryParameter(CODE_PARAM);
        if (!TextUtils.isEmpty(token)) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(context.getString(R.string.pref_access_token_key), token);
            editor.commit();
        }
    }

    public static String getToken(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(context.getString(R.string.pref_access_token_key), null);
    }
}