package com.blacky.mr.gifsearcher.Models;

public class GifList <T> {

    private T mData;
    private Meta mMeta;
    private Pagination mPagination;

    public T getData() {
        return mData;
    }

    public Meta getMeta() {
        return mMeta;
    }

    public Pagination getPagination() {
        return mPagination;
    }
}