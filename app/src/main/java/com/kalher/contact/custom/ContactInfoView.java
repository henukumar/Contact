package com.kalher.contact.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.kalher.contact.R;
import com.kalher.contact.databinding.LayoutContactInfoBinding;

public class ContactInfoView extends LinearLayout {

    private LayoutContactInfoBinding binding;

    public ContactInfoView(Context context) {
        this(context, null);
    }

    public ContactInfoView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ContactInfoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflateView(context);
    }

    private void inflateView(Context context){
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_contact_info, this, true);
        setOrientation(VERTICAL);
    }

    public void setData(String label, String value){
        binding.tvLabel.setText(label);
        binding.tvValue.setText(value);
    }

}
