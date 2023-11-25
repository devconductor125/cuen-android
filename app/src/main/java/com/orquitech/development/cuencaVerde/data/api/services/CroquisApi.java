package com.orquitech.development.cuencaVerde.data.api.services;

import com.orquitech.development.cuencaVerde.data.api.model.croquis.get.CroquisResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CroquisApi {

    @GET("{url}")
    Observable<List<CroquisResponse>> getCroquis(
            @Path(value = "url", encoded = true) String url,
            @Query("lat") double lat, @Query("lng") double lng,
            @Query("maxDistance") double maxDistance);
}
