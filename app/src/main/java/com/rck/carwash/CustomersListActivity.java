package com.rck.carwash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CustomersListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers_list);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        final CustomerListAdapter adapter = new CustomerListAdapter(new CustomerListAdapter.CustomerDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CustomerViewModel mCustomerViewModel = new ViewModelProvider(this).get(CustomerViewModel.class);
        // Update the cached copy of the words in the adapter.
        mCustomerViewModel.getAllCustomers().observe(this, adapter::submitList);

        floatingActionButton.setOnClickListener(v -> this.finish());

    }

}