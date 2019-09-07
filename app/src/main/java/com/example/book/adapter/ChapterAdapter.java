package com.example.book.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.book.database.Sqlite;
import com.example.book.model.Chapter;
import com.example.book.view.activity.Description;
import com.example.book.R;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.MyHolder> {
    private static final String TAG = "ChapterAdapter";
    private Context context;
    private List<Chapter> chapters;

    public ChapterAdapter(Context context, List<Chapter> chapters) {
        this.context = context;
        this.chapters = chapters;
    }

    @NonNull
    @Override
    public ChapterAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.recycler_view_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterAdapter.MyHolder myHolder, int i) {
        myHolder.textView.setText(chapters.get(i).getTitle());
        try {
            InputStream in = context.getAssets().open("images/" + chapters.get(i).getImage());
            Drawable drawable = Drawable.createFromStream(in, Integer.toString(i));
            Glide.with(context).load(drawable).into(myHolder.imageView);
        } catch (IOException e) {
            e.printStackTrace();
        }


        myHolder.layout.setOnClickListener(v -> {
            Intent intent = new Intent(context, Description.class);
            intent.putExtra("title", chapters.get(i).getTitle());
            intent.putExtra("image", chapters.get(i).getImage());
            intent.putExtra("content", chapters.get(i).getContent());
            context.startActivity(intent);
        });
        if (chapters.get(i).getFavorite() == 0) {
            myHolder.favorite.setImageDrawable(context.getResources().getDrawable(R.drawable.favorites_border));
            myHolder.favorite.setTag(0);
        } else {
            myHolder.favorite.setImageDrawable(context.getResources().getDrawable(R.drawable.favorites));
            myHolder.favorite.setTag(1);
        }
        myHolder.favorite.setOnClickListener(v -> {
            if (myHolder.favorite.getTag().equals(0)) {
                myHolder.favorite.setTag(1);
                try {
                    Log.i(TAG, "onBindViewHolder: " + i);
                    Log.i(TAG, "onBindViewHolder: get before " + Sqlite.getInstance(context).getChapterFavorite(chapters.get(i).getId()));
                    Sqlite.getInstance(context).setChapterFavorite(chapters.get(i).getId(), true);
                    Log.i(TAG, "onBindViewHolder: get after " + Sqlite.getInstance(context).getChapterFavorite(chapters.get(i).getId()));

                    Log.i(TAG, "onBindViewHolder: " + chapters.get(i).getFavorite());
                } catch (IOException e) {
                    Log.e(TAG, "onBindViewHolder: ", e);
                }
                myHolder.favorite.setImageDrawable(context.getResources().getDrawable(R.drawable.favorites));
                Toast.makeText(context, "Added to Favorites List", Toast.LENGTH_SHORT).show();
            } else {
                myHolder.favorite.setTag(0);
                try {
                    Log.i(TAG, "onBindViewHolder: get before " + Sqlite.getInstance(context).getChapterFavorite(chapters.get(i).getId()));
                    Sqlite.getInstance(context).setChapterFavorite(chapters.get(i).getId(), false);
                    Log.i(TAG, "onBindViewHolder: get after " + Sqlite.getInstance(context).getChapterFavorite(chapters.get(i).getId()));
                    Log.i(TAG, "onBindViewHolder: " + chapters.get(i).getFavorite());
                } catch (IOException e) {
                    Log.e(TAG, "onBindViewHolder: ", e);
                }
                myHolder.favorite.setImageDrawable(context.getResources().getDrawable(R.drawable.favorites_border));
                Toast.makeText(context, "Removed From Favorites List", Toast.LENGTH_SHORT).show();
                //notifyDataSetChanged();
            }
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
        ImageView favorite;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_imageView);
            textView = itemView.findViewById(R.id.item_title);
            layout = itemView.findViewById(R.id.item_view);
            favorite = itemView.findViewById(R.id.favorite_icon_recycler_view);
        }
    }
}
