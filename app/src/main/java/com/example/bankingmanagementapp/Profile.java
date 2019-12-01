package com.example.bankingmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class Profile extends AppCompatActivity {

    AutoCompleteTextView editTextUserName;
    EditText editTextAccountNo,editTextMircNo,editTextPinNo,editTextTotalAmount,editTextName,editTextMobile;
    Button buttonSearch,buttonEdit,buttonUpdate;
    RadioGroup radioGroup;
    RadioButton genderButton;

    String gender,accountNo,mircNo,pinNo,amount,name,phonNo,accountType;
    String [] adapterAccountType ={"Running","Current"};
    Spinner spinner;
    int amountId;



    public void clear(){

        editTextAccountNo.setText("");
        editTextMircNo.setText("");
        editTextPinNo.setText("");
        editTextTotalAmount.setText("");
        editTextName.setText("");
        editTextMobile.setText("");

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        editTextUserName = (AutoCompleteTextView) findViewById(R.id.profileSearchUsername);
        buttonSearch = (Button) findViewById(R.id.profileSearchButton);

        editTextAccountNo =(EditText) findViewById(R.id.profileAcNoId);
        editTextMircNo = (EditText) findViewById(R.id.profileAcMircNoId);
        editTextPinNo = (EditText) findViewById(R.id.profileAcPinNoId);

        spinner =(Spinner) findViewById(R.id.profileAcSpinnerId);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroupId);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,adapterAccountType);
        spinner.setAdapter(arrayAdapter);

        final Intent intent = getIntent();
        editTextAccountNo.setText(intent.getStringExtra("accountNo"));
        editTextMircNo.setText(intent.getStringExtra("mircNo"));
        editTextPinNo.setText(intent.getStringExtra("pinNo"));



    }
}


