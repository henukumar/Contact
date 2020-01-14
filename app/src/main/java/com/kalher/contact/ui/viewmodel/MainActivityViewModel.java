package com.kalher.contact.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.kalher.contact.common.Status;
import com.kalher.contact.datasource.db.entity.ContactEntity;
import com.kalher.contact.datasource.repository.ContactRepository;

public class MainActivityViewModel extends ViewModel {

    public String name;
    public int age;
    public long number;
    public String email;

    public void insertContact(LiveData<Status> status){
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setName(name);
        contactEntity.setAge(age);
        contactEntity.setNumber(number);
        contactEntity.setEmail(email);
        ContactRepository.insertContact(contactEntity, status);
    }

}
