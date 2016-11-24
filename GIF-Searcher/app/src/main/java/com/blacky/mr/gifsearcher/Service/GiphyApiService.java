package com.blacky.mr.gifsearcher.Service;


import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GiphyApiService {

    static String baseUrl = "http://api.giphy.com";
    static String giphyApiKey = "dc6zaTOxFJmzC";
    static  String keyParametr = "api_key";


    public static IGiphyApiService create() {

        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl url = request.url().
                        newBuilder().
                        addQueryParameter(keyParametr, giphyApiKey).
                        build();
                request = request.newBuilder().url(url).build();
                return chain.proceed(request);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .client(client)
                .build();

        return retrofit.create(IGiphyApiService.class);
    }

}
