package com.example.buyregister.APIHelper
import retrofit2.Call;
import retrofit2.http.GET;

public interface DolarAPI {
    @GET("data.json")
    fun getDolar(): Call<respuestaDolar>
}