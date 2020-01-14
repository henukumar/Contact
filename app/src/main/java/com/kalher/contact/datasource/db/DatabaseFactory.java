package com.kalher.contact.datasource.db;

import androidx.room.Room;

import com.kalher.contact.base.Contact;

public class DatabaseFactory {

    private static String CONTACT_DATABASE = "ContactDb";

    private static ContactDatabase dbInstance;

    private static final Object sLock = new Object();

    public static ContactDatabase getDbInstance(){
        synchronized (sLock){
            if(dbInstance == null){
                dbInstance = Room.databaseBuilder(Contact.getsApplicationContext(), ContactDatabase.class, CONTACT_DATABASE).build();
            }
            return dbInstance;
        }
    }

}
