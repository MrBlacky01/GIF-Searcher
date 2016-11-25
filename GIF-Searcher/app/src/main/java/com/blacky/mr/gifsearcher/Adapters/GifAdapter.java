package com.blacky.mr.gifsearcher.Adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.blacky.mr.gifsearcher.Models.ApplicationModels.GifData;
import com.blacky.mr.gifsearcher.R;
import com.blacky.mr.gifsearcher.ViewModel.GifViewModel;
import com.blacky.mr.gifsearcher.databinding.GifItemBinding;

import java.util.Collections;
import java.util.List;

public class GifAdapter  extends RecyclerView.Adapter<GifAdapter.ViewHolder>{

    private List<GifData> mDataList;
    private Context mContext;

    public GifAdapter(Context context) {
        mDataList = Collections.emptyList();
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        GifItemBinding gifBinding= DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.gif_item,
                parent,
                false);

        return new ViewHolder(gifBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.gifItemBinding.setViewModel(new GifViewModel(mContext, mDataList.get(position)));
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void addGifList(List<GifData> DataList){
        mDataList = DataList;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public GifItemBinding gifItemBinding;

        public ViewHolder(GifItemBinding gifItemBinding) {
            super(gifItemBinding.cardView);
            this.gifItemBinding = gifItemBinding;
        }
    }
}
