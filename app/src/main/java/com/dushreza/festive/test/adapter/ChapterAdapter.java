package com.dushreza.festive.test;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.MyHolder> {
    private Context context;
    private List<Chapter> chapters;

    public ChapterAdapter(Context context, List<Chapter> chapters) {
        this.context = context;
        this.chapters = chapters;
    }

    @NonNull
    @Override
    public ChapterAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterAdapter.MyHolder myHolder, int i) {
        switch (i) {
            case 0:
                myHolder.textView.setText(chapters.get(i).title);
                try {
                    InputStream in = context.getAssets().open("images/01.jpg");
                    Drawable drawable = Drawable.createFromStream(in, "01.jpg");
                    Glide.with(context).load(drawable).into(myHolder.imageView);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 1:
                myHolder.textView.setText(chapters.get(i).title);
                try {
                    InputStream in = context.getAssets().open("images/02.jpg");
                    Drawable drawable = Drawable.createFromStream(in, "02.jpg");
                    Glide.with(context).load(drawable).into(myHolder.imageView);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                myHolder.textView.setText(chapters.get(i).title);
                try {
                    InputStream in = context.getAssets().open("images/03.jpg");
                    Drawable drawable = Drawable.createFromStream(in, "03.jpg");
                    Glide.with(context).load(drawable).into(myHolder.imageView);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

        }


        myHolder.layout.setOnClickListener(v -> {
            Intent intent = new Intent(context, Description.class);
            intent.putExtra("title", chapters.get(i).title);
            intent.putExtra("image", chapters.get(i).image);
            intent.putExtra("content", chapters.get(i).content);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return chapters.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        RelativeLayout layout;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_imageView);
            textView = itemView.findViewById(R.id.item_title);
            layout = itemView.findViewById(R.id.item_view);
        }
    }
}
