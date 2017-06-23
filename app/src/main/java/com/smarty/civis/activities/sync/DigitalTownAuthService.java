package com.smarty.civis.activities.sync;

import com.smarty.civis.BuildConfig;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by anh.hoang on 6/22/17.
 */

public class DigitalTownAuthService {
    public static final String RESPONSE_TYPE = "code";
    public static final String SCOPE = "email";
    private static DigitalTownAuthService INSTANCE;

    private static final String URL = "https://v1-sso-api.digitaltown.com/oauth/";
    private Retrofit mRetrofit;
    private IDigitalTownAuth mDigitalTownAuth;

    private DigitalTownAuthService(){
        MoshiConverterFactory moshi = MoshiConverterFactory.create();
        moshi.asLenient();

        mRetrofit = new Retrofit
                .Builder()
                .baseUrl(URL)
                .addConverterFactory(moshi)
                .build();

        mDigitalTownAuth = mRetrofit.create(IDigitalTownAuth.class);
    }

    public Call<String> authorize(){
        return mDigitalTownAuth.authorize(BuildConfig.DT_CLIENT_ID, RESPONSE_TYPE, SCOPE);
    }

    public static DigitalTownAuthService getInstance(){
        if(INSTANCE == null){
            INSTANCE = new DigitalTownAuthService();
        }

        return INSTANCE;
    }


    interface IDigitalTownAuth{
        @GET("authorize")
        Call<String> authorize(@Query("client_id") String clientId, @Query("response_type") String responseType, @Query("scope") String scope);
    }
}
