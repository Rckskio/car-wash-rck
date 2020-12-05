package com.rck.carwash;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CustomerViewModel extends AndroidViewModel {
    private final CustomerRepository mRepository;

    private final LiveData<List<Customer>> mAllCustomers;

    public CustomerViewModel (Application application) {
        super(application);

        mRepository = new CustomerRepository(application);
        mAllCustomers = mRepository.getAllCustomers();
    }

    LiveData<List<Customer>> getAllCustomers() {
        return mAllCustomers;
    }

    public void insert(Customer customer) {
        mRepository.insert(customer);
    }
}
