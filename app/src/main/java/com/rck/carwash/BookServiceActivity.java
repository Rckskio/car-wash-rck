package com.rck.carwash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BookServiceActivity extends AppCompatActivity {
    private AutoCompleteTextView autoCompleteTextView;
    private BookViewModel mBookViewModel;
    private ArrayList<String> names;
    private ArrayAdapter<String> adapter;
    private RadioGroup radioGroup;
    private RadioButton selected;
    private TextView textViewDate;
    private TextView textViewTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_service);
        TextView appBarText = findViewById(R.id.app_title);
        appBarText.setText(R.string.btn_book_service);
        names = new ArrayList<>();
        autoCompleteTextView = findViewById(R.id.auto_input_name);
        radioGroup = findViewById(R.id.services);
        textViewDate = findViewById(R.id.text_view_date);
        textViewTime = findViewById(R.id.text_view_time);
        Button btn_confirmBook = findViewById(R.id.btn_confirm_book);
        btn_confirmBook.setBackgroundColor(getColor(R.color.disabled));
        btn_confirmBook.setEnabled(false);
        Button btn_cancel = findViewById(R.id.btn_cancel);
        btn_confirmBook.setOnClickListener(v -> handleConfirmBookClick());
        btn_cancel.setOnClickListener(v -> handleCancelClick());
        mBookViewModel = new ViewModelProvider(this).get(BookViewModel.class);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(adapter);
        CustomerViewModel mCustomerViewModel = new ViewModelProvider(this).get(CustomerViewModel.class);
        mCustomerViewModel.getAllCustomers().observe(this, customerList -> {
            for (Customer c : customerList) {
                names.add(c.name);
            }
            adapter.notifyDataSetChanged();
        });

        autoCompleteTextView.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                hideKeyboard(v);
            }
        });

        autoCompleteTextView.setOnDismissListener(() -> hideKeyboard(autoCompleteTextView));

        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && radioGroup.getCheckedRadioButtonId() != -1) {
                    btn_confirmBook.setBackgroundColor(getColor(R.color.btn_color));
                    btn_confirmBook.setEnabled(true);
                } else {
                    btn_confirmBook.setBackgroundColor(getColor(R.color.disabled));
                    btn_confirmBook.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (autoCompleteTextView.getText().length() < 1) {
                System.out.println("MESSAGE: empty text");
            } else {
                int id = radioGroup.getCheckedRadioButtonId();
                selected = findViewById(id);
                System.out.println("MESSAGE: other value of text" + " value = " + autoCompleteTextView.getText());
                btn_confirmBook.setBackgroundColor(getColor(R.color.btn_color));
                btn_confirmBook.setEnabled(true);
            }
        });

        textViewDate.setOnClickListener(v -> handleDate());
        textViewTime.setOnClickListener(v -> handleTime());

    }

    private void handleConfirmBookClick() {
        System.out.println("ACTIVITY: Confirm Book Button pressed");
        if (autoCompleteTextView.getText().length() < 1) {
            Toast.makeText(this, "Empty Customer Name", Toast.LENGTH_LONG).show();
        }
        if (textViewDate.getText().equals("Select Date") || textViewTime.getText().equals("Select Time")) {
            Toast.makeText(this, "You Must Select Date and Time", Toast.LENGTH_LONG).show();
        } else {
            String name = autoCompleteTextView.getText().toString();
            String date = textViewDate.getText().toString();
            String time = textViewTime.getText().toString();
            String service = selected.getText().toString();
            Book book = new Book();
            book.name = name;
            book.date = date;
            book.time = time;
            book.service = service;

            System.out.println("Name = " + book.name);
            System.out.println("Date = " + book.date);
            System.out.println("Time = " + book.time);
            System.out.println("Service = " + book.service);
            mBookViewModel.insert(book);
            Toast.makeText(
                    getApplicationContext(),
                    "Service Booked Successfully =)",
                    Toast.LENGTH_LONG).show();
        }
        this.finish();
    }

    private void handleCancelClick() {
        System.out.println("ACTIVITY: Cancel Button pressed");
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("ACTIVITY: BookService activity finished successfully");
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void handleDate() {
        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        long future = 15768000000L;

        // date picker dialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(BookServiceActivity.this, android.R.style.Theme_Material_Dialog_Alert,
                (view, year1, monthOfYear, dayOfMonth) -> {
                    Calendar cal = Calendar.getInstance();
                    cal.set(Calendar.YEAR, year1);
                    cal.set(Calendar.MONTH, monthOfYear);
                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    Date date = cal.getTime();
                    String dateText = new SimpleDateFormat("EEE, d MMM yyyy", Locale.ENGLISH).format(date);
                    textViewDate.setText(dateText);
                }, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() + future);
        datePickerDialog.show();

    }

    private void handleTime() {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        // date picker dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(BookServiceActivity.this, android.R.style.Theme_Material_Dialog_Alert,
                (view, hourOfDay, minute1) -> {
                    Calendar cal = Calendar.getInstance();
                    cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    cal.set(Calendar.MINUTE, minute1);
                    Date date = cal.getTime();
                    String time = new SimpleDateFormat("HH:mm a", Locale.ENGLISH).format(date);
                    textViewTime.setText(time);
                }, hour, minute, false);
        timePickerDialog.show();
    }
}