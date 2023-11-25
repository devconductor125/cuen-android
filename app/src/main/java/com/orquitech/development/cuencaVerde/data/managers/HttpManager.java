package com.orquitech.development.cuencaVerde.data.managers;

import com.orquitech.development.cuencaVerde.data.api.UploadListener;

import retrofit2.Retrofit;

public interface HttpManager {

    Retrofit getCuencaVerdeMockRetrofit(String accessToken);

    Retrofit getCroquisApiRetrofit();

    Retrofit getCuencaVerdeRetrofit(String accessToken);

    Retrofit getCuencaVerdeImagesRetrofit(String accessToken);

    Retrofit getOauthRetrofit(String accessToken);

    Retrofit getCuencaVerdeFilesUploadRetrofit(String accessToken, UploadListener listener);
}
