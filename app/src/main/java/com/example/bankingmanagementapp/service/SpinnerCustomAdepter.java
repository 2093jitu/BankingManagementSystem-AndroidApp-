package com.example.bankingmanagementapp.service;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bankingmanagementapp.R;
import com.example.bankingmanagementapp.model.Account;
import com.example.bankingmanagementapp.model.Balance;

import java.util.List;

public class SpinnerCustomAdepter extends BaseAdapter {


    List<Balance> allBalance;
    Context context;
    LayoutInflater inflater;


    public SpinnerCustomAdepter(List<Balance> allBalance, Context context) {
        this.allBalance = allBalance;
        this.context = context;
    }

    @Override
    public int getCount() {

        return allBalance.size();
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
            inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view= inflater.inflate(R.layout.sample_view_for_tranasfer_spiner,viewGroup,false);
        }

        TextView textView =(TextView) view.findViewById(R.id.textViewTrnasferSampleId);

        textView.setText(allBalance.get(i).getAccountNo());

        return view;












    }
}
