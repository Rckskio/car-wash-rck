package com.rck.carwash;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class BookListAdapter extends ListAdapter<Book, BookViewHolder> {

    public BookListAdapter(@NonNull DiffUtil.ItemCallback<Book> diffCallBack) {
        super(diffCallBack);
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return BookViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book current = getItem(position);
        holder.bind(current.name, current.date, current.time, current.service);
    }

    static class BookDiff extends DiffUtil.ItemCallback<Book> {

        @Override
        public boolean areItemsTheSame(@NonNull Book oldItem, @NonNull Book newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Book oldItem, @NonNull Book newItem) {
            return oldItem.name.equals(newItem.name);
        }
    }

}
