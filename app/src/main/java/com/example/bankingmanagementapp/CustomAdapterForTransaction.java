package com.example.bankingmanagementapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bankingmanagementapp.model.Balance;

import java.util.List;

public class CustomAdapterForTransaction extends BaseAdapter {


    List<Balance> balance;
    Context contex;
    LayoutInflater layoutInflater;

    public CustomAdapterForTransaction(List<Balance> balance, Context contex) {
        this.balance = balance;
        this.contex = contex;
    }

    public CustomAdapterForTransaction() {

    }

    @Override
    public int getCount() {
        return balance.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){
            layoutInflater = (LayoutInflater) contex.getSystemService(contex.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.sample_transaction_view,viewGroup,false);

        }

        TextView textViewName =(TextView) view.findViewById(R.id.nameId);
        TextView textViewCurrentBalance =(TextView) view.findViewById(R.id.currentBalanceId);
        TextView textViewAccountNo =(TextView) view.findViewById(R.id.accountNoId);
        TextView textViewMircNo =(TextView) view.findViewById(R.id.mricId);


        try {

            textViewName.setText("Name : "+balance.get(i).getName().toString());
            int balanceAmount =  balance.get(i).getAmmount();
            String balance1 = String.valueOf(balanceAmount);

            textViewCurrentBalance.setText("Current Balance : "+balance1);
            textViewAccountNo.setText("Account No : "+balance.get(i).getAccountNo().toString());
            textViewMircNo.setText("Mirc No : "+String.valueOf(balance.get(i).getMircNo()));

        }catch (Exception ex){


        }


        return view;



    }
}
