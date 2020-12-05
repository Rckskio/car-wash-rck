package com.rck.carwash;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Customer.class}, version = 1)
public abstract class CustomersRoomDatabase extends RoomDatabase {
    public abstract CustomerDao customerDao();

    private static volatile CustomersRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static CustomersRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CustomersRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CustomersRoomDatabase.class, "customers")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
