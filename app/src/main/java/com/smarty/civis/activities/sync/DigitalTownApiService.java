package com.smarty.civis.activities.sync;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

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
    }
}
