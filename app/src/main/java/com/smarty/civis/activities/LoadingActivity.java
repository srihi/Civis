package com.smarty.civis.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.smarty.civis.R;
import com.smarty.civis.models.User;
import com.smarty.civis.sync.DigitalTownApiService;
import com.smarty.civis.sync.TokenResponse;
import com.smarty.civis.utils.PrefUtils;

import retrofit2.Call;

public class LoadingActivity extends AppCompatActivity {
    private DigitalTownApiService mApiService = DigitalTownApiService.getInstance();
    private Call<User> mUserCall;
    private Call<TokenResponse> mTokenCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        String accessCode = PrefUtils.getCode(this);
        String token = PrefUtils.getToken(this);
        String refreshToken = PrefUtils.getRefreshToken(this);
        long time = PrefUtils.getExpireTime(this);
    }
}
