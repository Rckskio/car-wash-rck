package com.rck.carwash;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CustomerRepository {
    private final CustomerDao mCustomerDao;
    private final LiveData<List<Customer>> mAllCustomers;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples

    CustomerRepository(Application application) {
        CustomersRoomDatabase db = CustomersRoomDatabase.getDatabase(application);
        mCustomerDao = db.customerDao();
        mAllCustomers = mCustomerDao.getAllCustomers();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Customer>> getAllCustomers() {
        return mAllCustomers;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Customer customer) {
        CustomersRoomDatabase.databaseWriteExecutor.execute(() -> mCustomerDao.create(customer.name, customer.phone));
    }

}
