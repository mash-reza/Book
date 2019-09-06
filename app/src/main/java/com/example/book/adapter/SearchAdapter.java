package com.example.book.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.book.R;
import com.example.book.model.Chapter;

import java.util.List;

public class SearchAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Chapter> chapterList;

    public SearchAdapter(Context context, List<Chapter> chapterList) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.chapterList = chapterList;
    }

    @Override
    public int getCount() {
        return chapterList.size();
    }

    @Override
    public Object getItem(int position) {
        return chapterList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.search_list_item,parent,false);
            holder.textView = convertView.findViewById(R.id.list_item_search);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.textView.setText(chapterList.get(position).getTitle());
        return convertView;
    }

    static class ViewHolder{
        TextView textView;
    }
}
