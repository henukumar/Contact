package com.kalher.contact.ui.viewholder;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.kalher.contact.R;
import com.kalher.contact.databinding.LayoutContactItemBinding;
import com.kalher.contact.datasource.db.entity.ContactEntity;
import com.kalher.contact.ui.activity.ContactListActivity;
import com.kalher.contact.utils.ResourceUtility;
import com.kalher.contact.utils.StringUtility;

public class ContactViewHolder extends RecyclerView.ViewHolder {

    private LayoutContactItemBinding binding;
    private ContactListActivity.ItemSelectionListener mItemSelectionListener;

    public ContactViewHolder(ViewDataBinding viewDataBinding, ContactListActivity.ItemSelectionListener itemSelectionListener) {
        super(viewDataBinding.getRoot());
        mItemSelectionListener = itemSelectionListener;
        if(viewDataBinding instanceof LayoutContactItemBinding){
            binding = (LayoutContactItemBinding) viewDataBinding;
        }
    }

    public void bind(ContactEntity data){
        if(binding != null){
            if(StringUtility.isNonEmpty(data.getName())){
                binding.tvContactIcon.setText(data.getName().substring(0, 1));
//                binding.tvContactIcon.setBackground(ResourceUtility.getDrawable(R.drawable.drawable_circular_background));
                Drawable drawable = ResourceUtility.getDrawable(R.drawable.drawable_circular_background);
                drawable.setColorFilter(ResourceUtility.getRandomColor(getAdapterPosition()), PorterDuff.Mode.SRC_OVER);
                binding.tvContactIcon.setBackground(drawable);
            }
            binding.tvContactName.setText(data.getName());
            binding.tvContactNumber.setText(String.valueOf(data.getNumber()));

            binding.getRoot().setOnClickListener(v -> mItemSelectionListener.onItemSelected(data));

        }
    }


}
