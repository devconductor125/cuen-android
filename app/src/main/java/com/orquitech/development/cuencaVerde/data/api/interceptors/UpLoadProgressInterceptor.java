package com.orquitech.development.cuencaVerde.data.api.interceptors;

import com.orquitech.development.cuencaVerde.data.api.CountingRequestBody;
import com.orquitech.development.cuencaVerde.data.api.UploadListener;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class UpLoadProgressInterceptor implements Interceptor {

    private UploadListener uploadListener;

    public UpLoadProgressInterceptor(UploadListener uploadListener) {
        this.uploadListener = uploadListener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (null == request.body()) {
            return chain.proceed(request);
        }

        Request build = request.newBuilder()
                .method(request.method(), new CountingRequestBody(request.body(), uploadListener))
                .build();
        return chain.proceed(build);
    }
}
