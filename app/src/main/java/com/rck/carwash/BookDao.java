package com.rck.carwash;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookDao {
    @Query("INSERT INTO bookings (name, date, time, service) VALUES (:name, :date, :time, :service)")
    void create(String name, String date, String time, String service);

    @Query("SELECT * FROM bookings ORDER BY date ASC")
    LiveData<List<Book>> getAllBookings();
}
