package com.siddhantbhardwaj.contactsmanager;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Contacts.class},version=3)
public abstract class ContactDatabase extends RoomDatabase {

    public abstract ContactDao getContactDao();

    private static ContactDatabase dbInstance;

    public static synchronized ContactDatabase getInstance(Context context){
        if(dbInstance == null){
            dbInstance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    ContactDatabase.class,
                    "contacts"
            ).fallbackToDestructiveMigration().build();
        }
        return dbInstance;
    }


}
