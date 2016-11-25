package com.blacky.mr.gifsearcher.ViewModel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.blacky.mr.gifsearcher.Models.Data;
import com.blacky.mr.gifsearcher.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class GifViewModel {

    private Context mContext;
    private Data mGifResponse;

    public GifViewModel(Context context, Data gifResponse) {
        mContext = context;
        mGifResponse = gifResponse;
    }

    @BindingAdapter({"bind:gifUrl"})
    public static void loadGif(ImageView view, String gifUrl) {

        Glide.with(view.getContext())
                .load(gifUrl)
                .asBitmap()
                .into(view);
    }

    public String getGifUrl(){
        return mGifResponse.getGifs().getFixHeigthStill().getUrl();
    }

    private String getGifVideoUrl(){
        return mGifResponse.getGifs().getFixHeigth().getUrl();
    }

    public View.OnClickListener onClickGifImage() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        };
    }

    private void showDialog(){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
        View gifDialogView = LayoutInflater.from(mContext).inflate(R.layout.dialog_gif_show, null);
        alertDialogBuilder.setView(gifDialogView);

        ImageView gifOriginal = (ImageView) gifDialogView.findViewById(R.id.gif_dialog_imageView);

        Glide.with(mContext)
                .load(getGifVideoUrl())
                .asGif()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.drawable.orange_background)
                .into(gifOriginal);


        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();

    }

}
