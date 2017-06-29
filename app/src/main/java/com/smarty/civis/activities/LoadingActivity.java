package com.smarty.civis.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.smarty.civis.R;
import com.smarty.civis.data.TaskUpdateService;
import com.smarty.civis.models.User;
import com.smarty.civis.sync.DigitalTownApiService;
import com.smarty.civis.sync.TokenResponse;
import com.smarty.civis.utils.PrefUtils;

import java.net.HttpURLConnection;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadingActivity extends AppCompatActivity {
    private DigitalTownApiService mApiService = DigitalTownApiService.getInstance();
    private Call<User> mUserCall;
    private Call<TokenResponse> mTokenCall;
    private Callback<TokenResponse> mTokenCallback = new Callback<TokenResponse>() {
        @Override
        public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
            if (response.code() == HttpURLConnection.HTTP_OK) {
                TokenResponse tokenResponse = response.body();
                long expireTime = Calendar.getInstance().getTimeInMillis() + tokenResponse.getExpiresIn();
                PrefUtils.putToken(LoadingActivity.this, tokenResponse.getAccessToken());
                PrefUtils.putRefreshToken(LoadingActivity.this, tokenResponse.getRefreshToken());
                PrefUtils.putExpireTime(LoadingActivity.this, expireTime);

                if (mUserCall != null && !mUserCall.isCanceled()) {
                    mUserCall.cancel();
                    mUserCall.enqueue(mUserCallback);
                }
            } else {
                // Token callback doesn't work, just authorize again
                startLoginActivity();
            }
        }

        @Override
        public void onFailure(Call<TokenResponse> call, Throwable t) {
            startLoginActivity();
        }
    };
    private Callback<User> mUserCallback = new Callback<User>() {
        @Override
        public void onResponse(Call<User> call, Response<User> response) {
            if (response.code() == HttpURLConnection.HTTP_OK) {
                User user = response.body();
                PrefUtils.putUserId(LoadingActivity.this, user.getId());
                TaskUpdateService.insertNewUser(LoadingActivity.this, user.getContentValues());
                Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } else {
                // Something went wrong just login again?
                // Shouldn't reach here if tokens are valid based on previous checks
                startLoginActivity();
            }
        }

        @Override
        public void onFailure(Call<User> call, Throwable t) {
            // Something went wrong just login again?
            // Shouldn't reach here if tokens are valid based on previous checks
            startLoginActivity();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        String accessCode = PrefUtils.getCode(this);
        String token = PrefUtils.getToken(this);
        String refreshToken = PrefUtils.getRefreshToken(this);
        long time = PrefUtils.getExpireTime(this);

        if (accessCode == null) {
            startLoginActivity();
        } else {
            long currentTime = Calendar.getInstance().getTimeInMillis();
            if (currentTime < time) { // Token is still valid
                mUserCall = mApiService.getUser(token);
            } else {
                mTokenCall = mApiService.refreshToken(refreshToken);
            }
        }
    }

    private void startLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mTokenCall != null && !mTokenCall.isExecuted()) {
            mTokenCall.enqueue(mTokenCallback);
        } else if (mUserCall != null && !mUserCall.isExecuted()) {
            mUserCall.enqueue(mUserCallback);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mUserCall != null && !mUserCall.isCanceled()) {
            mUserCall.cancel();
        }
        if (mTokenCall != null && !mTokenCall.isCanceled()) {
            mTokenCall.cancel();
        }
    }
}
