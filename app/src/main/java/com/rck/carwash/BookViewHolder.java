package com.rck.carwash;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class BookViewHolder extends RecyclerView.ViewHolder {
    private final TextView nameItemView;
    private final TextView dateItemView;
    private final TextView serviceItemView;
    private final TextView timeItemView;

    private BookViewHolder(View itemView) {
        super(itemView);
        nameItemView = itemView.findViewById(R.id.book_name_textView);
        dateItemView = itemView.findViewById(R.id.book_date_textView);
        timeItemView = itemView.findViewById(R.id.book_time_textView);
        serviceItemView = itemView.findViewById(R.id.book_service_textView);
    }

    public void bind(String name, String date, String time, String service) {
        nameItemView.setText(name);
        dateItemView.setText(date);
        timeItemView.setText(time);
        serviceItemView.setText(service);
    }

    static BookViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_book_item, parent, false);
        return new BookViewHolder(view);
    }

}
