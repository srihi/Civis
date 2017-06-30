package com.smarty.civis.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.smarty.civis.R;

import static android.R.attr.data;

/**
 * Created by anh.hoang on 6/24/17.
 */

public class PrefUtils {
    private static final String CODE_PARAM = "code";

    public static void putToken(Context context, String token) {
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

    // Store authorization code in the preferences
    public static void putCode(Context context, Uri data) {
        String code = data.getQueryParameter(CODE_PARAM);
        if (!TextUtils.isEmpty(code)) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(context.getString(R.string.pref_code_key), code);
            editor.commit();
        }
    }

    public static String getCode(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(context.getString(R.string.pref_code_key), null);
    }

    public static void putRefreshToken(Context context, String refreshToken) {
        if (!TextUtils.isEmpty(refreshToken)) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(context.getString(R.string.pref_refresh_token_key), refreshToken);
            editor.commit();
        }
    }

    public static String getRefreshToken(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(context.getString(R.string.pref_refresh_token_key), null);
    }

    public static void putExpireTime(Context context, long time) {
        if (time > 0) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putLong(context.getString(R.string.pref_expire_time_key), time);
            editor.commit();
        }
    }

    public static long getExpireTime(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getLong(context.getString(R.string.pref_expire_time_key), -1);
    }

    public static void putUserId(Context context, int uid) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt(context.getString(R.string.pref_uid_key), uid);
            editor.commit();
    }

    public static int getUserId(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getInt(context.getString(R.string.pref_uid_key), -1);
    }
}
