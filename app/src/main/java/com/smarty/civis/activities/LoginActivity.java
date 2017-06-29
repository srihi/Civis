package com.smarty.civis.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.smarty.civis.R;
import com.smarty.civis.data.TaskUpdateService;
import com.smarty.civis.models.User;
import com.smarty.civis.sync.DigitalTownApiService;
import com.smarty.civis.sync.TokenResponse;
import com.smarty.civis.utils.AuthUtils;
import com.smarty.civis.utils.PrefUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private final String LOG_TAG = LoginActivity.class.getSimpleName();

    private boolean mLoginRequested = false;

    private DigitalTownApiService mApiService = DigitalTownApiService.getInstance();
    private Call<TokenResponse> mTokenCall;
    private Callback<TokenResponse> mTokenResponseCallback = new Callback<TokenResponse>() {

        @Override
        public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
            if (response.code() == HttpURLConnection.HTTP_OK) { // 200
                TokenResponse tokenResponse = response.body();
                long expireTime = Calendar.getInstance().getTimeInMillis() + tokenResponse.getExpiresIn();
                PrefUtils.putToken(LoginActivity.this, tokenResponse.getAccessToken());
                PrefUtils.putRefreshToken(LoginActivity.this, tokenResponse.getRefreshToken());
                PrefUtils.putExpireTime(LoginActivity.this, expireTime);

                if (mUserCall != null && !mUserCall.isCanceled()) {
                    mUserCall.cancel();
                }

                mUserCall = mApiService.getUser(tokenResponse.getAccessToken());
                mUserCall.enqueue(mUserCallback);
            } else {
                mLoginRequested = false;
                Toast.makeText(LoginActivity.this, R.string.login_failed, Toast.LENGTH_SHORT).show();
                try {
                    Log.e(LOG_TAG, response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onFailure(Call<TokenResponse> call, Throwable t) {
            Log.e(LOG_TAG, t.getMessage());
            mLoginRequested = false;
            Toast.makeText(LoginActivity.this, R.string.login_failed, Toast.LENGTH_SHORT).show();
        }
    };

    private Call<User> mUserCall;
    private Callback<User> mUserCallback = new Callback<User>() {
        @Override
        public void onResponse(Call<User> call, Response<User> response) {
            if (response.code() == HttpURLConnection.HTTP_OK) {
                User user = response.body();
                PrefUtils.putUserId(LoginActivity.this, user.getUuid());
                TaskUpdateService.insertNewUser(LoginActivity.this, user.getContentValues());
                startMainActivity();
            } else {
                mLoginRequested = false;
                Toast.makeText(LoginActivity.this, R.string.login_failed, Toast.LENGTH_SHORT).show();

                try {
                    Log.e(LOG_TAG, response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onFailure(Call<User> call, Throwable t) {
            Log.e(LOG_TAG, t.getMessage());
            mLoginRequested = false;
            Toast.makeText(LoginActivity.this, R.string.login_failed, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);


        // If we are already authorized go to MainActivity directly
//        if (!TextUtils.isEmpty(PrefUtils.getCode(this))) {
//            startActivity(new Intent(this, MainActivity.class));
//            finish();
//        }
    }

    public void skipLogin(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    /**
     * Attempts to log in.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    public void attemptLogin(View view) {
        mLoginRequested = true;
        Intent intent = AuthUtils.getAuthenticationIntent();
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Uri uri = getIntent().getData();
        if (uri != null) {
            String code = uri.getQueryParameter("code");
            if (code != null) {
                PrefUtils.putCode(this, uri);

                if (mTokenCall != null && !mTokenCall.isCanceled()) {
                    mTokenCall.cancel();
                }

                mTokenCall = mApiService.getToken(code);
                mTokenCall.enqueue(mTokenResponseCallback);
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mTokenCall != null && !mTokenCall.isCanceled()) {
            mTokenCall.cancel();
        }
        if (mUserCall != null && !mUserCall.isCanceled()) {
            mUserCall.cancel();
        }
    }

    private void startMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}

