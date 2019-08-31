package com.dushreza.festive.test;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.MyHolder> {
    private Context context;
    private List<Chapter> chapters;

    public ChapterAdapter(Context context,List<Chapter> chapters){
        this.context = context;
        this.chapters = chapters;
    }
    @NonNull
    @Override
    public ChapterAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterAdapter.MyHolder myHolder, int i) {
        myHolder.button.setOnClickListener(v -> {
            Intent intent = new Intent(context,Description.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return chapters.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        Button button;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.button);
        }
    }
}
