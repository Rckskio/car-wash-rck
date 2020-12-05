package com.rck.carwash;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class CustomerViewHolder extends RecyclerView.ViewHolder {
    private final TextView nameItemView;
    private final TextView phoneItemView;

    private CustomerViewHolder(View itemView) {
        super(itemView);
        nameItemView = itemView.findViewById(R.id.name_textView);
        phoneItemView = itemView.findViewById(R.id.phone_textView);
    }

    public void bind(String name, String phone) {
        nameItemView.setText(name);
        phoneItemView.setText(phone);
    }

    static CustomerViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new CustomerViewHolder(view);
    }
}
