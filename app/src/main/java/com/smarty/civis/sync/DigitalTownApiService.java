package com.smarty.civis.sync;

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
    private static DigitalTownApiService INSTANCE;

    private static final String URL = "https://api.digitaltown.com/";
    private Retrofit mRetrofit;
    private IDigitalTownApi mDigitalTownApi;

    private DigitalTownApiService(){
        mRetrofit = new Retrofit
                .Builder()
                .baseUrl(URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        mDigitalTownApi = mRetrofit.create(IDigitalTownApi.class);
    }


    public static DigitalTownApiService getInstance(){
        if(INSTANCE == null){
            INSTANCE = new DigitalTownApiService();
        }

        return INSTANCE;
    }


    interface IDigitalTownApi{
        @POST("sso/token")
        Call<TokenResponse> token(@Body TokenRequest tokenRequest);
        @POST("sso/refresh")
        Call<TokenResponse> refreshToken(@Body TokenRefreshRequest tokenRefreshRequest);
        @GET("sso/users")
        Call<User> user(@Header("authorization") String authorization);

        // Further implementation of currencies, wallets & Transactions
    }
}
