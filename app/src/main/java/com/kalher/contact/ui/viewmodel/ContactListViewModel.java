package com.kalher.contact.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kalher.contact.datasource.db.entity.ContactEntity;
import com.kalher.contact.datasource.repository.ContactRepository;

import java.util.List;

public class ContactListViewModel extends ViewModel {

    private LiveData<List<ContactEntity>> contactList = new MutableLiveData<>();

    public LiveData<List<ContactEntity>> getContactList(){
        ContactRepository.getOrderedContactList(contactList);
        return contactList;
    }

}
