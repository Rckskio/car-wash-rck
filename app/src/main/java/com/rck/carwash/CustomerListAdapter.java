package com.rck.carwash;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class CustomerListAdapter extends ListAdapter<Customer, CustomerViewHolder> {

    public CustomerListAdapter(@NonNull DiffUtil.ItemCallback<Customer> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return CustomerViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        Customer current = getItem(position);
        holder.bind(current.name, formatNumber(current.phone));
    }

    static class CustomerDiff extends DiffUtil.ItemCallback<Customer> {

        @Override
        public boolean areItemsTheSame(@NonNull Customer oldItem, @NonNull Customer newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Customer oldItem, @NonNull Customer newItem) {
            return oldItem.name.equals(newItem.name);
        }
    }

    // Format phone number to 99999 - 9999 usually used in São Paulo - Brazil or 9999 - 9999
    // Other areas of Brazil
    private String formatNumber(String mobile) {
        if (mobile.length() == 8) {
            return mobile.substring(0,4) + " - " + mobile.substring(4);
        }
        return mobile.substring(0,5) + " - " + mobile.substring(5);
    }
}
