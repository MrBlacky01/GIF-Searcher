package com.blacky.mr.gifsearcher.Activities;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.support.design.widget.Snackbar;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.blacky.mr.gifsearcher.Adapters.GifAdapter;
import com.blacky.mr.gifsearcher.Models.ApplicationModels.GifData;
import com.blacky.mr.gifsearcher.Models.GSON.Datum;
import com.blacky.mr.gifsearcher.Models.GSON.GiphyResponse;
import com.blacky.mr.gifsearcher.R;
import com.blacky.mr.gifsearcher.Service.GiphyApiService;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private GifAdapter gifAdapter;
    private String searchQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressBar = (ProgressBar) findViewById(R.id.loading_progress_bar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        gifAdapter = new GifAdapter(this);

        recyclerView.setAdapter(gifAdapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL));
        /*if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(
                    2, StaggeredGridLayoutManager.VERTICAL));
        }
        else {
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(
                    3, StaggeredGridLayoutManager.HORIZONTAL));
        }*/


        if (checkInternetConnection()) {
            new GetTrendingTask().execute();
        }
        else{
            Snackbar.make(recyclerView, R.string.no_internet, Snackbar.LENGTH_SHORT).show();
        }

        handleIntent(getIntent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));


        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    public void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            searchQuery = intent.getStringExtra(SearchManager.QUERY);

            if (checkInternetConnection()) {
                new GetSearchResultsTask().execute();
            }
            else {
                Snackbar.make(recyclerView, getString(R.string.no_internet), Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    public boolean checkInternetConnection() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return connectivityManager.getActiveNetworkInfo() != null &&
                connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }


    class GetTrendingTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            GiphyApiService.create().getGifs().enqueue(new Callback<GiphyResponse>() {
                @Override
                public void onResponse(Call<GiphyResponse> call, Response<GiphyResponse> response) {

                    if(response.code() == HttpURLConnection.HTTP_OK){
                        List<Datum> gifResponseList = response.body().getData();
                        ArrayList<GifData> gifList = new ArrayList<GifData>();
                        for(int i = 0; i < gifResponseList.size(); i++){
                            gifList.add(new GifData(gifResponseList.get(i).getImages().getFixedHeightSmallStill().getUrl(),
                                    gifResponseList.get(i).getImages().getOriginal().getUrl()));
                        }
                        if(gifList.size() != 0 ){
                            gifAdapter.addGifList(gifList);
                        }
                    }else {
                        Snackbar.make(recyclerView,"Can't load Gifs ", Snackbar.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GiphyResponse> call, Throwable t) {
                    Log.e("onFailure", "Trending API call failed.");
                    t.printStackTrace();
                    Snackbar.make(recyclerView,"Can't load Gifs ", Snackbar.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            });
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
        }
    }

    class GetSearchResultsTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            GiphyApiService.create().searchGif(searchQuery).enqueue(new Callback<GiphyResponse>() {
                @Override
                public void onResponse(Call<GiphyResponse> call, Response<GiphyResponse> response) {

                    if(response.code() == HttpURLConnection.HTTP_OK){
                        List<Datum> gifResponseList = response.body().getData();
                        ArrayList<GifData> gifList = new ArrayList<>();
                        for(int i = 0; i < gifResponseList.size(); i++){
                            gifList.add(new GifData(gifResponseList.get(i).getImages().getFixedHeightSmallStill().getUrl(),
                                    gifResponseList.get(i).getImages().getOriginal().getUrl()));
                        }
                        if(gifList.size() != 0 ){
                            gifAdapter.addGifList(gifList);
                        }
                    }else {
                        Snackbar.make(recyclerView,"Can't load Gifs ", Snackbar.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GiphyResponse> call, Throwable t) {
                    Log.e("onFailure", "Search API call failed.");
                    t.printStackTrace();
                    Snackbar.make(recyclerView,"Can't load Gifs ", Snackbar.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            });
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);

        }
    }
}
