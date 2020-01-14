package com.kalher.contact.ui.bottomsheet;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.kalher.contact.R;
import com.kalher.contact.base.Contact;
import com.kalher.contact.databinding.LayoutBottomSheetBinding;
import com.kalher.contact.datasource.db.entity.ContactEntity;

public class ContactBottomSheet extends BottomSheetDialogFragment implements View.OnClickListener {

    private BottomSheetBehavior bottomSheetBehavior;
    private LayoutBottomSheetBinding binding;
    private ContactEntity contactEntity;

    private static final String CONST_CONTACT = "contact";

    public static ContactBottomSheet getInstance(ContactEntity contactEntity){
        ContactBottomSheet contactBottomSheet = new ContactBottomSheet();
        Bundle args = new Bundle();
        args.putSerializable(CONST_CONTACT, contactEntity);
        contactBottomSheet.setArguments(args);
        return contactBottomSheet;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            Bundle data = getArguments();
            contactEntity = (ContactEntity) data.getSerializable(CONST_CONTACT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_bottom_sheet, container,false);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        setUI();
        setClickListeners();
    }

    private void setClickListeners(){
        binding.civNumber.setOnClickListener(this);
    }

    private void setUI(){
        bottomSheetBehavior = BottomSheetBehavior.from((View) binding.getRoot().getParent());
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        if(contactEntity != null){
            binding.tvContactName.setText(contactEntity.getName());
            binding.civNumber.setData(getString(R.string.hint_number), contactEntity.getNumber() + "");
            binding.civAge.setData(getString(R.string.hint_age), contactEntity.getAge() + "");
            binding.civEmail.setData(getString(R.string.hint_email), contactEntity.getEmail());
        }

        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if(newState == BottomSheetBehavior.STATE_DRAGGING){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {}
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.civ_number:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + contactEntity.getNumber()));
                startActivity(intent);
                break;
        }
    }
}
