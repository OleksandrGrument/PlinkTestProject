package com.grument.plinktestproject.retrofit;

import com.grument.plinktestproject.dto.WorldNewsResultsDTO;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;


public interface RetrofitService {

    @GET("world.json")
    Observable<Response<WorldNewsResultsDTO>> getWorldNews(@Header("api-key") String apiKey);

}


