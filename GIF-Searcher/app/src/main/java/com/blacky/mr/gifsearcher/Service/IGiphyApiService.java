package com.blacky.mr.gifsearcher.Service;

import com.blacky.mr.gifsearcher.Models.Data;
import com.blacky.mr.gifsearcher.Models.Gif;
import com.blacky.mr.gifsearcher.Models.GifList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IGiphyApiService {

    @GET("v1/gifs/trending")
    Call<GifList<ArrayList<Data>>>  getGifs(@Query("limit")int limit);

    @GET("v1/gifs/search")
    Call<GifList<ArrayList<Data>>> searchGif(@Query("q")String query, @Query("limit")int limit);

}
