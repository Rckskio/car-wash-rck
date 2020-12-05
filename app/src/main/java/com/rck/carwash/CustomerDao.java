package com.rck.carwash;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CustomerDao {
    @Query("INSERT INTO customers (name, phone) VALUES (:name, :phone)")
    void create(String name, String phone);

    @Query("SELECT * FROM customers ORDER BY name ASC")
    LiveData<List<Customer>> getAllCustomers();

}
