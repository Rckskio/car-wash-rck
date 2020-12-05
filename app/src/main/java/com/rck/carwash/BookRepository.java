package com.rck.carwash;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class BookRepository {
    private final BookDao mBookDao;
    private final LiveData<List<Book>> mAllBookings;

    BookRepository(Application application) {
        BookingsRoomDatabase db = BookingsRoomDatabase.getDatabase(application);
        mBookDao = db.bookDao();
        mAllBookings = mBookDao.getAllBookings();
    }

    LiveData<List<Book>> getAllBookings() {return mAllBookings;}

    void insert(Book book) {
        BookingsRoomDatabase.databaseWriteExecutor.execute(() -> mBookDao.create(book.name, book.date, book.time, book.service));
    }
}
