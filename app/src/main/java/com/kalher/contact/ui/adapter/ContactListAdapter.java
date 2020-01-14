package com.kalher.contact.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.kalher.contact.R;
import com.kalher.contact.datasource.db.entity.ContactEntity;
import com.kalher.contact.ui.activity.ContactListActivity;
import com.kalher.contact.ui.viewholder.ContactViewHolder;

import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ContactEntity> mDataList;
    private ContactListActivity.ItemSelectionListener mItemSelectionListener;

    public ContactListAdapter(List<ContactEntity> dataList, ContactListActivity.ItemSelectionListener itemSelectionListener){
        mDataList = dataList;
        mItemSelectionListener = itemSelectionListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding viewDataBinding  = DataBindingUtil.inflate(inflater, R.layout.layout_contact_item, parent, false);
        return new ContactViewHolder(viewDataBinding, mItemSelectionListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ContactViewHolder) holder).bind(mDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

}
