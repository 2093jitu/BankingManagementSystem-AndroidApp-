
package com.example.bankingmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.view.View;
import android.widget.Toast;

import com.example.bankingmanagementapp.connection.AppConnection;
import com.example.bankingmanagementapp.model.Account;
import com.example.bankingmanagementapp.service.GetDataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerList extends AppCompatActivity {

    ListView listView;
    List<Account> country;
    int  img=R.drawable.thinking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);
        listView= findViewById(R.id.listViewId);

        GetDataService service = AppConnection.getRetrofitInstance().create(GetDataService.class);
        Call<List<Account>> call = service.getAccount();

        call.enqueue(new Callback<List<Account>>() {

            @Override
            public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {

               country= response.body();
                CustomAdapter adepter = null;
                try {
                    adepter = new CustomAdapter(getApplicationContext(),country,img);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                listView.setAdapter(adepter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent = new Intent(CustomerList.this,CustomerDetailsPage.class);
                        intent.putExtra("name",country.get(position).getBalance().getName());
                        intent.putExtra("acNo",country.get(position).getBalance().getAccountNo());
                        intent.putExtra("dob",country.get(position).getDob());
                        intent.putExtra("accType",country.get(position).getAccountType());
                        intent.putExtra("gender",country.get(position).getGender());
                        intent.putExtra("mob",country.get(position).getPhon());
                        startActivity(intent);
                        country.get(position).getAccountType().toString();
                    }
                });

                Log.d("okkkkkkkkkkkkkk" ,country.toString());
            }


            @Override
            public void onFailure(Call<List<Account>> call, Throwable t) {

            }
        });


    }





}
