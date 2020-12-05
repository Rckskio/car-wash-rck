package com.rck.carwash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CustomerSignInActivity extends AppCompatActivity {
    private TextInputEditText editTextName;
    private TextInputEditText editTextPhone;
    private CustomerViewModel mCustomerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_sign_in);

        System.out.println("ACTIVITY: Customer Sign in activity started");

        editTextName = findViewById(R.id.name_input);
        editTextPhone = findViewById(R.id.phone_input);
        int textDefaultColor = editTextPhone.getCurrentTextColor();
        Button btn_save = findViewById(R.id.btn_save);
        btn_save.setBackgroundColor(getColor(R.color.disabled));
        btn_save.setEnabled(false);
        editTextPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 8 && validatePhone(s.toString())) {
                    btn_save.setBackgroundColor(getColor(R.color.btn_color));
                    btn_save.setEnabled(true);
                } else {
                    btn_save.setBackgroundColor(getColor(R.color.disabled));
                    btn_save.setEnabled(false);
                }
                if (!validatePhone(s.toString()) && s.length() != 0) {
                    editTextPhone.setTextColor(getColor(R.color.red));
                } else {
                    editTextPhone.setTextColor(textDefaultColor);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        Button btn_cancel = findViewById(R.id.btn_cancel);
        btn_save.setOnClickListener(v -> handleSaveClick());
        btn_cancel.setOnClickListener(v -> handleCancelClick());
        mCustomerViewModel = new ViewModelProvider(this).get(CustomerViewModel.class);
    }

    private void handleSaveClick() {
        System.out.println("ACTIVITY: Save Button pressed");
        if (TextUtils.isEmpty(editTextName.getText()) || TextUtils.isEmpty(editTextPhone.getText())) {
            Toast.makeText(
                    getApplicationContext(),
                    "You must fill up both fields!",
                    Toast.LENGTH_LONG).show();
        } else if(validatePhone(editTextPhone.getText().toString())){
            String name = capitalizeAll(editTextName.getText().toString());
            System.out.println("NAME: " + name);
            String phone = editTextPhone.getText().toString();
            Customer customer = new Customer();
            customer.name = name;
            customer.phone = phone;
            mCustomerViewModel.insert(customer);
            finish();
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Phone field must contain only numbers",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void handleCancelClick() {
        System.out.println("ACTIVITY: Cancel Button pressed");
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("ACTIVITY: Customer Sign in activity finished successfully");
    }

    private String capitalizeAll(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        str = str.trim().replaceAll("\\s+", " ");
        str = str.replaceAll("\\p{Punct}", "");
        str = str.toLowerCase();

        return Arrays.stream(str.split("\\s+"))
                .map(t -> t.substring(0, 1).toUpperCase() + t.substring(1))
                .collect(Collectors.joining(" "));
    }

    private boolean validatePhone(String phone) {
        return phone.matches("[0-9]+");
    }
}