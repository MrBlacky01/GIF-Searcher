package com.blacky.mr.gifsearcher.Models.ApplicationModels;

public class GifData {
    private String mImageUrl;
    private String mGifUrl;

    public GifData(String imageUrl, String gifUrl) {
        mImageUrl = imageUrl;
        mGifUrl = gifUrl;
    }


    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public String getGifUrl() {
        return mGifUrl;
    }

    public void setGifUrl(String gifUrl) {
        mGifUrl = gifUrl;
    }
}
