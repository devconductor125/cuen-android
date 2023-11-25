package com.orquitech.development.cuencaVerde.data.api.interceptors;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptorFilesUpload implements Interceptor {

    private final String accessToken;

    public HeaderInterceptorFilesUpload(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public Response intercept(Chain chain)
            throws IOException {
        Request request = chain.request();
        request = request.newBuilder()
                .addHeader("Content-Type", "multipart/form-data")
                .addHeader("Authorization", "Bearer " + accessToken)
                .build();
        return chain.proceed(request);
    }
}
