package com.blacky.mr.gifsearcher.Models;


public class Data {
    private String mType;
    private String mId;
    private String mSlug;
    private String mUrl;
    private String mBitlyGifUrl;
    private String mBitlyUrl;
    private String mEmbedUrl;
    private String mUsername;
    private String mSource;
    private String mRating;
    private String mContentUrl;
    private String mSourceTld;
    private String mSourcePostUrl;
    private Integer mIsIndexable;
    private String mImportDatetime;
    private String mTrendingDatetime;
    private Gif mGifs;

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getSlug() {
        return mSlug;
    }

    public void setSlug(String slug) {
        mSlug = slug;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getBitlyGifUrl() {
        return mBitlyGifUrl;
    }

    public void setBitlyGifUrl(String bitlyGifUrl) {
        mBitlyGifUrl = bitlyGifUrl;
    }

    public String getBitlyUrl() {
        return mBitlyUrl;
    }

    public void setBitlyUrl(String bitlyUrl) {
        mBitlyUrl = bitlyUrl;
    }

    public String getEmbedUrl() {
        return mEmbedUrl;
    }

    public void setEmbedUrl(String embedUrl) {
        mEmbedUrl = embedUrl;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getSource() {
        return mSource;
    }

    public void setSource(String source) {
        mSource = source;
    }

    public String getRating() {
        return mRating;
    }

    public void setRating(String rating) {
        mRating = rating;
    }

    public String getContentUrl() {
        return mContentUrl;
    }

    public void setContentUrl(String contentUrl) {
        mContentUrl = contentUrl;
    }

    public String getSourceTld() {
        return mSourceTld;
    }

    public void setSourceTld(String sourceTld) {
        mSourceTld = sourceTld;
    }

    public String getSourcePostUrl() {
        return mSourcePostUrl;
    }

    public void setSourcePostUrl(String sourcePostUrl) {
        mSourcePostUrl = sourcePostUrl;
    }

    public Integer getIsIndexable() {
        return mIsIndexable;
    }

    public void setIsIndexable(Integer isIndexable) {
        mIsIndexable = isIndexable;
    }

    public String getImportDatetime() {
        return mImportDatetime;
    }

    public void setImportDatetime(String importDatetime) {
        mImportDatetime = importDatetime;
    }

    public String getTrendingDatetime() {
        return mTrendingDatetime;
    }

    public void setTrendingDatetime(String trendingDatetime) {
        mTrendingDatetime = trendingDatetime;
    }

    public Gif getGifs() {
        return mGifs;
    }

    public void setGifs(Gif gifs) {
        mGifs = gifs;
    }
}
