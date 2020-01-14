package com.kalher.contact.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.kalher.contact.R;
import com.kalher.contact.utils.StringUtility;

public class CustomInputField extends TextInputLayout {

    private InputType inputType;

    private TextInputEditText editText;

    public CustomInputField(Context context) {
        this(context, null);
    }

    public CustomInputField(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomInputField(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflateView(context, attrs);
    }

    private void inflateView(Context context, AttributeSet attributeSet){
        TypedArray attributeTypedArray = context.obtainStyledAttributes(attributeSet, R.styleable.custom_input_field);
        try{
            inputType = InputType.values()[attributeTypedArray.getInt(R.styleable.custom_input_field_input_type, 0)];
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            attributeTypedArray.recycle();
        }
        init();
        setListeners();
    }

    private void init() {
        editText = new TextInputEditText(getContext());
        addView(editText);

        editText.setInputType(inputType.getInputType());
    }

    private void setListeners(){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(getError() != null){
                    setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    public boolean isValid(){
        switch (inputType){
            case NAME:
            case AGE:
            case NUMBER:
            case EMAIL:
                String value = editText.getText().toString();
                boolean isValid = value.trim().matches(inputType.getRegex());
                if(!isValid){
                    setError("Please enter correct " + getHint().toString().toLowerCase());
                }
                return isValid;
        }
        return true;
    }

    public String getText(){
        return editText.getText().toString();
    }

    public void setText(String text){
        if(StringUtility.isNonEmpty(text)){
            editText.setText(text);
        }
    }

    public void clearText(){
        editText.setText("");
    }

}
