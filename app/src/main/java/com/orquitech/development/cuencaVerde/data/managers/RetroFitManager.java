package com.orquitech.development.cuencaVerde.data.managers;

import com.orquitech.development.cuencaVerde.App;
import com.orquitech.development.cuencaVerde.data.api.UploadListener;
import com.orquitech.development.cuencaVerde.data.api.cuencaVerdeApi.ApiConfig;
import com.orquitech.development.cuencaVerde.data.api.interceptors.CuencaVerdeApiInterceptor;
import com.orquitech.development.cuencaVerde.data.api.interceptors.HeaderInterceptor;
import com.orquitech.development.cuencaVerde.data.api.interceptors.HeaderInterceptorFilesUpload;
import com.orquitech.development.cuencaVerde.data.api.interceptors.HeaderInterceptorOauth;
import com.orquitech.development.cuencaVerde.data.api.interceptors.UpLoadProgressInterceptor;
import com.orquitech.development.cuencaVerde.data.api.serializers.InternalIdModelExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitManager implements HttpManager {

    private final App app;
    private final ApiConfig apiConfig;

    public RetroFitManager(App app, ApiConfig apiConfig) {
        this.app = app;
        this.apiConfig = apiConfig;
    }

    @Override
    public Retrofit getCuencaVerdeMockRetrofit(String accessToken) {

        Gson gson = new GsonBuilder()
                .addSerializationExclusionStrategy(new InternalIdModelExclusionStrategy())
                .serializeNulls()
                .create();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // add your other interceptors …
        httpClient.addInterceptor(new HeaderInterceptor(accessToken));
        httpClient.addInterceptor(new CuencaVerdeApiInterceptor()); // TODO For testing
        // add logging as last interceptor
        httpClient.addInterceptor(logging);

        return new Retrofit.Builder()
                .baseUrl(apiConfig.getCuencaVerdeDbApiUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient.build())
                .build();
    }

    @Override
    public Retrofit getCroquisApiRetrofit() {
        Gson gson = new GsonBuilder()
                .addSerializationExclusionStrategy(new InternalIdModelExclusionStrategy())
                .serializeNulls()
                .create();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        return new Retrofit.Builder()
                .baseUrl(apiConfig.getCroquisApiUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient.build())
                .build();
    }

    @Override
    public Retrofit getCuencaVerdeRetrofit(String accessToken) {
        Gson gson = new GsonBuilder()
                .addSerializationExclusionStrategy(new InternalIdModelExclusionStrategy())
                .serializeNulls()
                .create();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new HeaderInterceptor(accessToken));
        httpClient.addInterceptor(logging);

        return new Retrofit.Builder()
                .baseUrl(apiConfig.getCuencaVerdeDbApiUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient.build())
                .build();
    }

    @Override
    public Retrofit getCuencaVerdeImagesRetrofit(String accessToken) {
        Gson gson = new GsonBuilder()
                .addSerializationExclusionStrategy(new InternalIdModelExclusionStrategy())
                .serializeNulls()
                .create();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new HeaderInterceptor(accessToken));
        httpClient.addInterceptor(logging);

        return new Retrofit.Builder()
                .baseUrl(apiConfig.getCuencaVerdeImagesUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient.build())
                .build();
    }

    @Override
    public Retrofit getOauthRetrofit(String accessToken) {
        Gson gson = new GsonBuilder()
                .addSerializationExclusionStrategy(new InternalIdModelExclusionStrategy())
                .serializeNulls()
                .create();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new HeaderInterceptorOauth(accessToken));
        httpClient.addInterceptor(logging);

        return new Retrofit.Builder()
                .baseUrl(apiConfig.getCuencaVerdeDbApiUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient.build())
                .build();
    }

    @Override
    public Retrofit getCuencaVerdeFilesUploadRetrofit(String accessToken, UploadListener listener) {
        Gson gson = new GsonBuilder()
                .addSerializationExclusionStrategy(new InternalIdModelExclusionStrategy())
                .serializeNulls()
                .create();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new HeaderInterceptorFilesUpload(accessToken));
        httpClient.addInterceptor(new UpLoadProgressInterceptor(listener));
        httpClient.addInterceptor(logging);

        return new Retrofit.Builder()
                .baseUrl(apiConfig.getCuencaVerdeDbApiUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient.build())
                .build();
    }
}
