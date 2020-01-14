package com.kalher.contact.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.kalher.contact.R;
import com.kalher.contact.base.BaseActivity;
import com.kalher.contact.base.Contact;
import com.kalher.contact.common.Status;
import com.kalher.contact.databinding.ActivityMainBinding;
import com.kalher.contact.ui.viewmodel.MainActivityViewModel;
import com.kalher.contact.utils.StringUtility;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private ActivityMainBinding binding;

    private MainActivityViewModel mViewModel;

    private final String CONST_NAME = "name";
    private final String CONST_AGE = "age";
    private final String CONST_NUMBER = "number";
    private final String CONST_EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.toolbar);

        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        setClickListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_show_contact:
                Intent intent = new Intent(this, ContactListActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(CONST_NAME, binding.etName.getText());
        outState.putString(CONST_AGE, binding.etAge.getText());
        outState.putString(CONST_NUMBER, binding.etNumber.getText());
        outState.putString(CONST_EMAIL, binding.etEmail.getText());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        binding.etName.setText(savedInstanceState.getString(CONST_NAME));
        binding.etAge.setText(savedInstanceState.getString(CONST_AGE));
        binding.etNumber.setText(savedInstanceState.getString(CONST_NUMBER));
        binding.etEmail.setText(savedInstanceState.getString(CONST_EMAIL));
    }

    private void setClickListeners(){
        binding.btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add:
                validateAndAddContact();
                break;
        }
    }

    private void validateAndAddContact(){
        boolean isValid = true;
        if(!binding.etName.isValid()){
            isValid = false;
        }else{
            mViewModel.name = binding.etName.getText();
        }
        if(!binding.etAge.isValid()){
            isValid = false;
        }else{
            mViewModel.age = Integer.parseInt(binding.etAge.getText());
        }
        if(!binding.etNumber.isValid()){
            isValid = false;
        }else{
            mViewModel.number = Long.parseLong(binding.etNumber.getText());
        }
        if(!binding.etEmail.isValid()){
            isValid = false;
        }else {
            mViewModel.email = binding.etEmail.getText();
        }

        LiveData<Status> status = new MutableLiveData<>();
        ((MutableLiveData) status).setValue(Status.PENDING);

        status.observe(this, status1 -> {
            if(status1 == Status.SUCCESS){
                Toast.makeText(Contact.getsCurrentBaseActivity(),
                        Contact.getsCurrentBaseActivity().getString(R.string.msg_contact_added),
                        Toast.LENGTH_SHORT).show();
                clearForm();
            }else if(status1 == Status.FAILED){
                Toast.makeText(Contact.getsCurrentBaseActivity(),
                        Contact.getsCurrentBaseActivity().getString(R.string.msg_some_error_occured),
                        Toast.LENGTH_SHORT).show();
            }
        });

        if(isValid){
            mViewModel.insertContact(status);
        }

    }

    private void clearForm(){
        binding.etName.clearText();
        binding.etAge.clearText();
        binding.etNumber.clearText();
        binding.etEmail.clearText();
        mViewModel.name = "";
        mViewModel.age = 0;
        mViewModel.number = 0;
        mViewModel.email = "";
    }

}
