package com.kalher.contact.custom;

public enum InputType {

    TEXT(android.text.InputType.TYPE_CLASS_TEXT, ".*"),
    NAME(android.text.InputType.TYPE_CLASS_TEXT, "[a-zA-Z\\s]+"),
    AGE(android.text.InputType.TYPE_CLASS_NUMBER, "[0-9]{1,3}"),
    NUMBER(android.text.InputType.TYPE_CLASS_PHONE, "[0-9]{10,12}"),
    EMAIL(android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS,
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    private int inputType;
    private String regex;

    InputType(int inputType, String regex){
        this.inputType = inputType;
        this.regex = regex;
    }

    public int getInputType() {
        return inputType;
    }

    public String getRegex(){
        return regex;
    }

}
