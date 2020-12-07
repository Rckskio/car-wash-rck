package com.rck.carwash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BookingsCalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings_calendar);
        TextView appBarText = findViewById(R.id.app_title);
        appBarText.setText(R.string.btn_bookings_calendar);

        RecyclerView recyclerView = findViewById(R.id.recyclerview_bookings);
        FloatingActionButton floatingActionButton = findViewById(R.id.fab_back);
        final BookListAdapter adapter = new BookListAdapter(new BookListAdapter.BookDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        BookViewModel bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);
        bookViewModel.getAllBookings().observe(this, adapter::submitList);

        floatingActionButton.setOnClickListener(v -> this.finish());
    }
}