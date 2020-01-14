package com.kalher.contact.datasource.repository;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kalher.contact.R;
import com.kalher.contact.base.Contact;
import com.kalher.contact.common.Status;
import com.kalher.contact.datasource.db.DatabaseFactory;
import com.kalher.contact.datasource.db.dao.ContactDao;
import com.kalher.contact.datasource.db.entity.ContactEntity;

import java.util.List;

public class ContactRepository {

    public static void insertContact(ContactEntity contact, LiveData<Status> status){
        ContactDao contactDao = DatabaseFactory.getDbInstance().getContactDao();
        Contact.getExecutor().execute(() -> {
            long id = contactDao.insertContact(contact);
            ((MutableLiveData) status).postValue(id != -1 ? Status.SUCCESS : Status.FAILED);
//            notifyUser(id);
        });
    }

    public static void getOrderedContactList(LiveData contactListLiveData){
        ContactDao contactDao = DatabaseFactory.getDbInstance().getContactDao();
        Contact.getExecutor().execute(() -> {
            List<ContactEntity> contactList = contactDao.getOrderedContacts();
            ((MutableLiveData) contactListLiveData).postValue(contactList);
        });
    }

    private static void notifyUser(long id){
        Message message = new Message();
        if(id == -1){
            message.arg1 = -1;
        }else {
            message.arg1 = 0;
        }

        Handler handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                if (id != -1) {
                    Toast.makeText(Contact.getsCurrentBaseActivity(),
                            Contact.getsCurrentBaseActivity().getString(R.string.msg_contact_added),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Contact.getsCurrentBaseActivity(),
                            Contact.getsCurrentBaseActivity().getString(R.string.msg_some_error_occured),
                            Toast.LENGTH_SHORT).show();
                }
            }
        };
        handler.sendMessage(message);
    }

}
