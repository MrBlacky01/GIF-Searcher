package com.blacky.mr.gifsearcher.Models;

public class Pagination {

    private Integer mTotalCount;
    private Integer mCount;
    private Integer mOffset;

    public Integer getTotalCount() {
        return mTotalCount;
    }

    public void setTotalCount(Integer totalCount) {
        mTotalCount = totalCount;
    }

    public Integer getCount() {
        return mCount;
    }

    public void setCount(Integer count) {
        mCount = count;
    }

    public Integer getOffset() {
        return mOffset;
    }

    public void setOffset(Integer offset) {
        mOffset = offset;
    }
}
