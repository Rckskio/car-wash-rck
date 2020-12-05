package com.rck.carwash;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Book.class}, version = 1)
public abstract class BookingsRoomDatabase extends RoomDatabase {
    public abstract BookDao bookDao();

    private static volatile BookingsRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static BookingsRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (BookingsRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            BookingsRoomDatabase.class, "bookings")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
