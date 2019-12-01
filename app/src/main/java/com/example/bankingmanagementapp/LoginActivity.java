package com.example.bankingmanagementapp;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bankingmanagementapp.connection.AppConnection;
import com.example.bankingmanagementapp.model.Account;
import com.example.bankingmanagementapp.model.Balance;
import com.example.bankingmanagementapp.service.GetDataService;
import com.example.bankingmanagementapp.sharedpreference.SPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class LoginActivity extends AppCompatActivity {

    TextView username;
    TextView password;
    Button login;
    Button createAccount;
    GetDataService service;
    Call<List<Account>> call;
    String accountNumber;
    String userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences sp = SPreferences.getSharedPreferences(getApplicationContext());
        String accNo =sp.getString("accountNo", "");
        if(accNo  != ""){

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);

        }
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        createAccount = findViewById(R.id.createAccount);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                accountNumber = username.getText().toString();
                userPassword = password.getText().toString();

                if( ! accountNumber.isEmpty() && ! userPassword.isEmpty()){

                    service = AppConnection.getRetrofitInstance().create(GetDataService.class);

                    call= service.getAccountByAccountNo(accountNumber);

                    call.enqueue(new Callback<List<Account>>() {
                        @Override
                        public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {

                            Log.d("Response" ,response.toString());

                            if(response.body().size()>0){

                                List <Account> account =   response.body();
                                String actualAccountNo =account.get(0).getBalance().getAccountNo();
                                String  actualPassword =String.valueOf(account.get(0).getPinNo());


                                if(actualAccountNo.equals(accountNumber) && actualPassword.equals(userPassword)){

                                    SharedPreferences sp = SPreferences.getSharedPreferences(getApplicationContext());
                                    SharedPreferences.Editor editor =sp.edit();
                                    editor.putString("accountNo",actualAccountNo);
                                    editor.commit();

                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);

                                }else{

                                    Toast.makeText(LoginActivity.this, "Password InCorrect", Toast.LENGTH_LONG).show();

                                }
                                Log.d("Account from login " ,account.toString());

                            }else{

                                Toast.makeText(LoginActivity.this, "Account No Is Incorrect", Toast.LENGTH_LONG).show();

                            }

                        }

                        @Override
                        public void onFailure(Call<List<Account>> call, Throwable t) {

                        }
                    });


                }else{

                    Toast.makeText(LoginActivity.this, "Empty", Toast.LENGTH_LONG).show();

                }

                username.setText("");
                password.setText("");


            }
        });


        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Random ran1 = new Random();
                int accountNo = ran1.nextInt(11111111);
                String accountNoString=String.valueOf(accountNo);

                int mircNo = ran1.nextInt(2222);
                String mircNoString=String.valueOf(mircNo);

                int pinNo = ran1.nextInt(7777);
                String pinNoString=String.valueOf(pinNo);

                Intent intent = new Intent(LoginActivity.this, CreateAccount.class);
                intent.putExtra("accountNo",accountNoString);
                intent.putExtra("mircNo",mircNoString);
                intent.putExtra("pinNo",pinNoString);
                startActivity(intent);
            }
        });

    }
}



