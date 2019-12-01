package com.example.bankingmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.bankingmanagementapp.connection.AppConnection;
import com.example.bankingmanagementapp.model.Account;
import com.example.bankingmanagementapp.model.Balance;
import com.example.bankingmanagementapp.model.Statement;
import com.example.bankingmanagementapp.service.GetDataService;
import com.example.bankingmanagementapp.sharedpreference.SPreferences;

import java.util.List;

import retrofit2.Call;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button depositeButton,withdrawButton,transferButton,transactionButton,statementButton,customerButton,profileButton,logOutButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        depositeButton=(Button)findViewById(R.id.buttonDiposit);
        withdrawButton=(Button) findViewById(R.id.buttonWithdraw);
        transferButton=(Button)findViewById(R.id.buttonTransfer);
        transactionButton=(Button) findViewById(R.id.buttonTransaction);
        statementButton=(Button)findViewById(R.id.buttonStatement);
        customerButton=(Button) findViewById(R.id.buttonCustomerList);
        profileButton=(Button)findViewById(R.id.buttonProfile);
        logOutButton=(Button) findViewById(R.id.buttonLogout);
        depositeButton.setOnClickListener(this);
        withdrawButton.setOnClickListener(this);
        transferButton.setOnClickListener(this);
        transactionButton.setOnClickListener(this);
        statementButton.setOnClickListener(this);
        customerButton.setOnClickListener(this);
        profileButton.setOnClickListener(this);
        logOutButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.buttonDiposit){
            Intent intent = new Intent(MainActivity.this,Deposite.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.buttonWithdraw){
            Intent intent = new Intent(MainActivity.this,Withdraw.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.buttonCustomerList){
            Intent intent = new Intent(MainActivity.this,CustomerList.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.buttonTransaction){
            Intent intent = new Intent(MainActivity.this,Transaction.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.buttonTransfer){
            Intent intent = new Intent(MainActivity.this,Transfer.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.buttonStatement){
            Toast.makeText(MainActivity.this, "Account No Is Incorrect", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this,StatementPage.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.buttonProfile){
            Intent intent = new Intent(MainActivity.this,Profile.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.buttonLogout){
            SharedPreferences sp = SPreferences.getSharedPreferences(getApplicationContext());
            sp.edit().remove("accountNo").commit();


            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        }

    }
}