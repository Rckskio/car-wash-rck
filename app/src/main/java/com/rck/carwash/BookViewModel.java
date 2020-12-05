package com.rck.carwash;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class BookViewModel extends AndroidViewModel {
    private final BookRepository mRepository;

    private final LiveData<List<Book>> mAllBookings;

    public BookViewModel (Application application) {
        super(application);
        mRepository = new BookRepository(application);
        mAllBookings = mRepository.getAllBookings();
    }

    LiveData<List<Book>> getAllBookings() {return mAllBookings;}

    public void insert(Book book) {mRepository.insert(book);}
}
