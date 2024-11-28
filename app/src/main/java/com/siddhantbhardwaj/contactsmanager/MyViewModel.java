package com.siddhantbhardwaj.contactsmanager;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MyViewModel extends AndroidViewModel {

    private Repository repository;

    private LiveData<List<Contacts>> allContacts;

    public MyViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<List<Contacts>> getAllContacts(){
        allContacts = repository.getAllContacts();
        return allContacts;
    }

    public void addContact(Contacts contact){
        repository.addContact(contact);
    }

    public void deleteContact(Contacts contact){
        repository.deleteContact(contact);
    }


}
