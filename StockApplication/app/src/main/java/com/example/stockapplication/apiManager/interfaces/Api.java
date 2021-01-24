package com.example.stockapplication.apiManager.interfaces;

import com.example.stockapplication.model.*;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface Api {
    String BASE_URL = "https://apidojo-yahoo-finance-v1.p.rapidapi.com/";

    @GET("market/auto-complete")
    Call<StockModel> viewStock(@Query("query") String querys, @Query("region") String region, @Header("X-RapidAPI-Key")String keyApi, @Header("X-RapidAPI-Host") String host);

}
