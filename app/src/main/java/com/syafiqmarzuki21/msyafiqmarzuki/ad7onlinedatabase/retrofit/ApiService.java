package com.syafiqmarzuki21.msyafiqmarzuki.ad7onlinedatabase.retrofit;

import com.syafiqmarzuki21.msyafiqmarzuki.ad7onlinedatabase.model.ResponseMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie/popular")
    Call<ResponseMovie> ambilDataMovie(
            @Query("api_key") String apikey
    );


}
