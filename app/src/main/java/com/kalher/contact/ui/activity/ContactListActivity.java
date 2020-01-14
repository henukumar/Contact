package com.kalher.contact.ui.activity;

import android.content.ClipData;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kalher.contact.R;
import com.kalher.contact.base.BaseActivity;
import com.kalher.contact.databinding.ActivityContactListBinding;
import com.kalher.contact.datasource.db.entity.ContactEntity;
import com.kalher.contact.ui.adapter.ContactListAdapter;
import com.kalher.contact.ui.bottomsheet.ContactBottomSheet;
import com.kalher.contact.ui.viewmodel.ContactListViewModel;

import java.util.List;

public class ContactListActivity extends BaseActivity {

    private ActivityContactListBinding binding;
    private ContactListAdapter mContactListAdapter;
    private ContactListViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contact_list);
        mViewModel = ViewModelProviders.of(this).get(ContactListViewModel.class);

        setUI();
    }

    private void setUI(){
        LiveData<List<ContactEntity>> contactList = mViewModel.getContactList();
        contactList.observe(this, contactEntities -> {
            setContactList(contactEntities);
        });
    }

    private void setContactList(List<ContactEntity> contactEntities) {
        if (mContactListAdapter == null) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            mContactListAdapter = new ContactListAdapter(contactEntities, new ItemSelectionListener(){
                @Override
                public void onItemSelected(ContactEntity contactEntity) {
                    openContactBottomSheet(contactEntity);
                }
            });
            binding.rvContactList.setLayoutManager(linearLayoutManager);
            binding.rvContactList.setAdapter(mContactListAdapter);
        } else {
            mContactListAdapter.notifyDataSetChanged();
        }
    }

    private void openContactBottomSheet(ContactEntity contactEntity){
        ContactBottomSheet contactBottomSheet = ContactBottomSheet.getInstance(contactEntity);
        contactBottomSheet.show(getSupportFragmentManager(), contactBottomSheet.getTag());
    }

    public interface ItemSelectionListener{
        void onItemSelected(ContactEntity contactEntity);
    }

}
