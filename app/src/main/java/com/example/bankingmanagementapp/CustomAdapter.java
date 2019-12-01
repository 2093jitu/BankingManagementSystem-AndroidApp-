package com.example.bankingmanagementapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bankingmanagementapp.model.Account;

import java.util.ArrayList;
import java.util.List;


public class CustomAdapter  extends BaseAdapter {
   /* String [] country;*/
    List<Account> country;
    int  img;
    Context context;
    LayoutInflater inflater;


    CustomAdapter(Context context,List<Account> country,int  img){
        this.context=context;
        this.img=img;
        this.country = country;
    }
    @Override
    public int getCount() {
        return country.size();
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
            view= inflater.inflate(R.layout.sample_view,viewGroup,false);
        }
        ImageView imge=(ImageView) view.findViewById(R.id.imageViewId);
        TextView textView =(TextView) view.findViewById(R.id.countryNameId);
        TextView textViewAccountNo =(TextView) view.findViewById(R.id.countryDescriptionId);

        imge.setImageResource(img);
        textView.setText("Name : "+country.get(i).getBalance().getName().toString());
        textViewAccountNo.setText("Account No : "+country.get(i).getBalance().getAccountNo().toString());
        return view;
    }
}