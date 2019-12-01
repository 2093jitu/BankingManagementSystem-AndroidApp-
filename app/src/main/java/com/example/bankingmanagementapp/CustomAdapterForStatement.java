package com.example.bankingmanagementapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bankingmanagementapp.model.Statement;

import java.text.SimpleDateFormat;
import java.util.List;

public class CustomAdapterForStatement extends BaseAdapter {


    List<Statement> statements;
    Context context;
    LayoutInflater inflater;


    public CustomAdapterForStatement(List<Statement> statements, Context context) {
        this.statements = statements;
        this.context = context;
    }

    public CustomAdapterForStatement() {
    }

    @Override
    public int getCount() {
        return statements.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(R.layout.simple_statement_view,parent,false);
        }



        TextView textView1 = (TextView) convertView.findViewById(R.id.allStatementId);
        TextView textView3 = (TextView) convertView.findViewById(R.id.statementTransactionDateId);
        TextView textView4 = (TextView) convertView.findViewById(R.id.statementDepoBalanceId);
        TextView textView5 = (TextView) convertView.findViewById(R.id.statementWithdrowBalanceId);
        TextView textView6 = (TextView) convertView.findViewById(R.id.statementTrnasferAmountId);
        TextView textView7 = (TextView) convertView.findViewById(R.id.statementCrAccNoId);
        TextView textView8 = (TextView) convertView.findViewById(R.id.statementDrAccNoId);
        TextView textView9 = (TextView) convertView.findViewById(R.id.statementCrAcountId);
        TextView textView10 = (TextView) convertView.findViewById(R.id.statementTotalbalanceId);


        int withdrowBalance=statements.get(position).getWithdrowBalance();
        int tranasferAmount =statements.get(position).getTransferAmount();
        String crAccountNo = statements.get(position).getCrAccountNo().toString();
        String drAccount = statements.get(position).getDrAccount().toString();
        int crAccount=statements.get(position).getCrAccount();
        int totalBalance =statements.get(position).getTotalbalance();


        Log.d("Statement new ",String.valueOf(withdrowBalance).toString());
        String pattern = "MM-dd-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);


       StringBuilder resuld = new StringBuilder();


        /*if(! statements.get(position).getTransectionDate().toString().isEmpty()){
            textView3.setText("Transaction Date : "+simpleDateFormat.format(statements.get(position).getTransectionDate()));
        }
        if( statements.get(position).getDipoBalance()>0){
            textView4.setText("Deposit Balance : "+String.valueOf(statements.get(position).getDipoBalance()));
        }if(statements.get(position).getWithdrowBalance()>0){

            textView5.setText("withdrow Balance : "+String.valueOf(statements.get(position).getWithdrowBalance()).toString());

            }
        if(tranasferAmount>0){

            textView6.setText("Tranasfer Amount : "+String.valueOf(tranasferAmount).toString());

        }
        if(!crAccountNo.equals("-")){

            textView7.setText("Creditted Account No : "+crAccountNo);
        }
        if(!drAccount.equals("-")){

            textView8.setText("Drbited Account No : "+drAccount);
        }
        if(crAccount>0){

            textView9.setText("Credit  Amount : "+String.valueOf(crAccount));

        }if(totalBalance>0){

            textView10.setText("Total Balance : "+String.valueOf(totalBalance));

        }
        return convertView;*/


        if(! statements.get(position).getTransectionDate().toString().isEmpty()){

            textView1.setText("Transaction Date : "+simpleDateFormat.format(statements.get(position).getTransectionDate()) +"\n");

        }
        if( statements.get(position).getDipoBalance()>0){

            textView1.setText("Diposit Balance : "+String.valueOf(statements.get(position).getDipoBalance()) +"\n");


        }if(statements.get(position).getWithdrowBalance()>0){


            textView1.setText("withdrow Balance : "+String.valueOf(statements.get(position).getWithdrowBalance()).toString() +"\n");

        }
        if(tranasferAmount>0){


            textView1.setText("Tranasfer Amount : "+String.valueOf(tranasferAmount).toString() +"\n");

        }
        if(!crAccountNo.equals("-")){


            textView1.setText("Creditted Account No : "+crAccountNo +"\n");
        }
        if(!drAccount.equals("-")){


           // textView1.setText("Drbited Account No : "+drAccount);
        }
        if(crAccount>0){

            //textView1.setText("Credit  Amount : "+String.valueOf(crAccount));


        }if(totalBalance>0){

           // textView1.setText("Total Balance : "+String.valueOf(totalBalance));


        }
        return convertView;















































    }


}
