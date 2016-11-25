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

    private static String baseUrl = "http://api.giphy.com";
    private static String giphyApiKey = "dc6zaTOxFJmzC";
    private static  String keyParametr = "api_key";


    public static IGiphyApiService create() {

            return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        HttpUrl url = request.url().newBuilder().
                                addQueryParameter(keyParametr,giphyApiKey).
                                build();
                        request = request.newBuilder().url(url).build();
                        return chain.proceed(request);
                    }
                }).build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IGiphyApiService.class);

    }

}
