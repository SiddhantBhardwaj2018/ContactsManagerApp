package com.siddhantbhardwaj.contactsmanager;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {

    private final ContactDao contactDao;
    ExecutorService executor;
    Handler handler;

    public Repository(Application application) {
        ContactDatabase contactDatabase = ContactDatabase.getInstance(application);
        this.contactDao = contactDatabase.getContactDao();
        executor = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());
    }

    public void addContact(Contacts contact){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactDao.insert(contact);
            }
        });
    }

    public void deleteContact(Contacts contact){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactDao.delete(contact);
            }
        });
    }

    public LiveData<List<Contacts>> getAllContacts(){
        return contactDao.getAllContacts();
    }

}
