package com.kalher.contact.datasource.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.kalher.contact.datasource.db.dao.ContactDao;
import com.kalher.contact.datasource.db.entity.ContactEntity;

@Database(entities = {ContactEntity.class}, version = 1, exportSchema = false)
public abstract class ContactDatabase extends RoomDatabase {

    public abstract ContactDao getContactDao();

}
