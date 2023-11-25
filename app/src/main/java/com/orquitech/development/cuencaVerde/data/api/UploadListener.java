package com.orquitech.development.cuencaVerde.data.api;

public interface UploadListener {

    void onRequestProgress(long bytesWritten, long contentLength);
}
