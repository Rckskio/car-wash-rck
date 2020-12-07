package com.rck.carwash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnRecord = findViewById(R.id.btn_record);
        Button btnBookService = findViewById(R.id.btn_book_service);
        Button btnBookingsCalendar = findViewById(R.id.btn_bookings_calendar);
        Button btnCustomersList = findViewById(R.id.btn_customers_list);

        btnRecord.setOnClickListener(v -> handleRecord());
        btnBookService.setOnClickListener(v -> handleBookService());
        btnBookingsCalendar.setOnClickListener(v -> handleBookingsCalendar());
        btnCustomersList.setOnClickListener(v -> handleCustomersList());

    }

    private void handleRecord() {
        Intent intent = new Intent(this, RecordCustomerActivity.class);
        startActivity(intent);
    }

    private void handleBookService() {
        Intent intent = new Intent(this, BookServiceActivity.class);
        startActivity(intent);
    }

    private void handleBookingsCalendar() {
        Intent intent = new Intent(this, BookingsCalendarActivity.class);
        startActivity(intent);
    }

    private void handleCustomersList() {
        Intent intent = new Intent(this, CustomersListActivity.class);
        startActivity(intent);
    }
}