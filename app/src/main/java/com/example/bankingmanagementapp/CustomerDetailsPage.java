package com.example.bankingmanagementapp;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CustomerDetailsPage extends AppCompatActivity {
TextView textViewName,textViewAccountNo,textViewDob,textViewAccountType,textViewGender,textViewMob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details_page);
        textViewName=(TextView) findViewById(R.id.customerNameId);

        textViewAccountNo=(TextView) findViewById(R.id.customerAcNoId);
        textViewDob=(TextView) findViewById(R.id.customerDobId);
        textViewAccountType=(TextView) findViewById(R.id.customerAcTypeId);
        textViewGender=(TextView) findViewById(R.id.customerGenderId);
        textViewMob=(TextView) findViewById(R.id.customerMobileNoId);

        try {
            Intent intent = getIntent();
            textViewName.setText("Name : "+intent.getStringExtra("name").toString());
            textViewAccountNo.setText("Account No : "+intent.getStringExtra("acNo").toString());
            textViewDob.setText("Birth Date : "+intent.getStringExtra("dob").toString());
            textViewAccountType.setText("Account Type : "+intent.getStringExtra("accType").toString());
            textViewGender.setText("Gender : "+intent.getStringExtra("gender").toString());
            textViewMob.setText("Mobile No : "+intent.getStringExtra("mob").toString());

        }catch (Exception ex){

            Toast.makeText(CustomerDetailsPage.this,"Same Value Is Missing",Toast.LENGTH_SHORT).show();
        }

    }
}
