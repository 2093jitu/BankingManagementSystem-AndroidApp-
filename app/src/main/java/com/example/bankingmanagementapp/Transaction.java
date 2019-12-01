package com.example.bankingmanagementapp;

import androidx.appcompat.app.AppCompatActivity;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;
import android.widget.SearchView;


import com.example.bankingmanagementapp.connection.AppConnection;
import com.example.bankingmanagementapp.model.Account;
import com.example.bankingmanagementapp.model.Balance;
import com.example.bankingmanagementapp.service.GetDataService;

import java.util.List;

public class Transaction extends AppCompatActivity {

    ListView listView;
    List<Balance> balance2;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        listView= (ListView) findViewById(R.id.transactionListId);
        searchView = (SearchView) findViewById(R.id.simpleSearchView);

        GetDataService service = AppConnection.getRetrofitInstance().create(GetDataService.class);
        Call<List<Balance>> call = service.getAllBalance();

        call.enqueue(new Callback<List<Balance>>() {
            @Override
            public void onResponse(Call<List<Balance>> call, Response<List<Balance>> response) {

                 balance2=response.body();

                Log.d("all Balance " ,balance2.toString());

               //CustomAdapterForTransaction adepter = null;



                    Log.d(" in all Balance " ,balance2.toString());
                  CustomAdapterForTransaction adepter = new CustomAdapterForTransaction(balance2,getApplicationContext());



                listView.setAdapter(adepter);
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String s) {
                        if (TextUtils.isEmpty(s)) {
                            listView.clearTextFilter();
                        }
                        else {
                            listView.setFilterText(s);
                        }
                        //adepter.getItem().filter(s);
                        return true;
                    }
                });


            }

            @Override
            public void onFailure(Call<List<Balance>> call, Throwable t) {

            }
        });
    }
}
