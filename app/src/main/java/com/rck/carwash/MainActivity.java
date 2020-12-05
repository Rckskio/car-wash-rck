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

        Button btnSignIn = findViewById(R.id.btn_sign_in);
        Button btnBookService = findViewById(R.id.btn_book_service);
        Button btnCalendar = findViewById(R.id.btn_calendar);
        Button btnCustomersList = findViewById(R.id.btn_customers_list);

        btnSignIn.setOnClickListener(v -> handleSignIn());
        btnBookService.setOnClickListener(v -> handleBookService());
        btnCalendar.setOnClickListener(v -> handleCalendar());
        btnCustomersList.setOnClickListener(v -> handleCustomersList());

    }

    private void handleSignIn() {
        System.out.println("ACTIVITY: Button SignIn pressed");
        Intent intent = new Intent(this, CustomerSignInActivity.class);
        startActivity(intent);
    }

    private void handleBookService() {
        System.out.println("ACTIVITY: Button Book Service pressed");
        Intent intent = new Intent(this, BookServiceActivity.class);
        startActivity(intent);
    }

    private void handleCalendar() {
        System.out.println("ACTIVITY: Button Calendar pressed");
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }

    private void handleCustomersList() {
        System.out.println("ACTIVITY: Button Customers List pressed");
        Intent intent = new Intent(this, CustomersListActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("ACTIVITY: Main Activity finished successfully");
    }
}