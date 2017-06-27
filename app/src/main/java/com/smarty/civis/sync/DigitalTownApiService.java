package com.smarty.civis.sync;

import com.smarty.civis.BuildConfig;
import com.smarty.civis.models.User;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Anh.Hoang on 6/22/2017.
 */

public class DigitalTownApiService {
    private static final String URL = "https://api.digitaltown.com/";
    private static final String REDIRECT_URI = "http://auth/callback";
    private static DigitalTownApiService INSTANCE;
    private final String TOKEN_GRANT_TYPE = "authorization_code";
    private final String REFRESH_TOKEN_SCOPE = "email";
    private final String REFRESH_TOKEN_GRANT_TYPE = "refresh_token";

    private Retrofit mRetrofit;
    private IDigitalTownApi mDigitalTownApi;

    private DigitalTownApiService() {
        mRetrofit = new Retrofit
                .Builder()
                .baseUrl(URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        mDigitalTownApi = mRetrofit.create(IDigitalTownApi.class);
    }


    public static DigitalTownApiService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DigitalTownApiService();
        }

        return INSTANCE;
    }

    public Call<TokenResponse> getToken(String authorizationCode) {
        TokenRequest request = new TokenRequest(
                TOKEN_GRANT_TYPE,
                BuildConfig.DT_CLIENT_ID,
                BuildConfig.DT_CLIENT_SECRET,
                REDIRECT_URI,
                authorizationCode);

        return mDigitalTownApi.token(request);
    }

    public Call<TokenResponse> refreshToken(String refreshToken) {
        TokenRefreshRequest request = new TokenRefreshRequest(
                REFRESH_TOKEN_GRANT_TYPE,
                BuildConfig.DT_CLIENT_ID,
                BuildConfig.DT_CLIENT_SECRET,
                refreshToken,
                REFRESH_TOKEN_SCOPE
        );

        return mDigitalTownApi.refreshToken(request);
    }

    public Call<User> getUser(String token) {
        return mDigitalTownApi.user(token);
    }


    interface IDigitalTownApi {
        @POST("sso/token")
        Call<TokenResponse> token(@Body TokenRequest tokenRequest);

        @POST("sso/refresh")
        Call<TokenResponse> refreshToken(@Body TokenRefreshRequest tokenRefreshRequest);

        @GET("sso/users")
        Call<User> user(@Header("authorization") String authorization);

        // Further implementation of currencies, wallets & Transactions
    }
}
