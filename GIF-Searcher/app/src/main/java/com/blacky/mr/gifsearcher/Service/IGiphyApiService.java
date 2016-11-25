package com.blacky.mr.gifsearcher.Service;

import com.blacky.mr.gifsearcher.Models.GSON.GiphyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IGiphyApiService {

    @GET("v1/gifs/trending")
    Call<GiphyResponse>  getGifs();

    @GET("v1/gifs/search")
    Call<GiphyResponse> searchGif(@Query("q")String query);

}
