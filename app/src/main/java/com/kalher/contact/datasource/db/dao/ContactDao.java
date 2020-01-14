package com.kalher.contact.datasource.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.kalher.contact.datasource.db.entity.ContactEntity;

import java.util.List;

@Dao
public interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertContact(ContactEntity contactEntity);

    @Query("SELECT * FROM contact ORDER BY name COLLATE NOCASE ASC")
    List<ContactEntity> getOrderedContacts();

}
