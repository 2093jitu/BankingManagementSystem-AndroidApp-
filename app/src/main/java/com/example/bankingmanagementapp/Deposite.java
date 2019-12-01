package com.example.bankingmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bankingmanagementapp.connection.AppConnection;
import com.example.bankingmanagementapp.model.Account;
import com.example.bankingmanagementapp.model.Balance;
import com.example.bankingmanagementapp.service.GetDataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Deposite extends AppCompatActivity {

    EditText editTextName,editTextAccountNo,editTexAvailableBalance,editTexDepositAmount,editTextTotalAmount;
    Button buttonSearch,buttonTotal,buttonDeposit;
    AutoCompleteTextView editText;
    int id;
    int depoAmmount;
    GetDataService service,service2,autuComplit;
    Call<List<Balance>> call;
    Call <String> call2;
    int availableBalance;
    Call<List<String>> allUserName;
    List<String> name;


    public void clearfild(){

        editText.setText("");
        editTextName.setText("");
        editTextAccountNo.setText("");
        editTexAvailableBalance.setText("");
        editTexDepositAmount.setText("");
        editTextTotalAmount.setText("");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposite);

        editText = (AutoCompleteTextView) findViewById(R.id.actv);
        buttonSearch =(Button) findViewById(R.id.depoSearchButtonId);
        editTextName =(EditText) findViewById(R.id.depoNameId);
        editTextAccountNo =(EditText) findViewById(R.id.depoAccountNoId);
        editTexAvailableBalance =(EditText) findViewById(R.id.depoAvailableBalanceId);
        editTexDepositAmount=(EditText) findViewById(R.id.dipoAmountid);
        editTextTotalAmount=(EditText) findViewById(R.id.dipoTotalAmountid);
        buttonTotal=(Button) findViewById(R.id.depoTotalButtonId);
        buttonDeposit=(Button) findViewById(R.id.depoBalanceButtonId);


        autuComplit = AppConnection.getRetrofitInstance().create(GetDataService.class);
        allUserName = autuComplit.getAllUserName();

        allUserName.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {

                name = response.body();
                Log.d("Auto Complit Depo" ,name.toString());
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.custom_list_item_for_withdrow,R.id.text_view_list_item_withdrow,name);
                editText.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName=  editText.getText().toString();

                if(! userName.isEmpty()){

                    service = AppConnection.getRetrofitInstance().create(GetDataService.class);
                    call = service.getBalance(userName);

                    call.enqueue(new Callback<List<Balance>>() {
                        @Override
                        public void onResponse(Call<List<Balance>> call, Response<List<Balance>> response) {

                            if(response.body().size()>0){
                                List<Balance> balances= response.body();
                                id=(Integer)balances.get(0).getId();
                                String  balanceName=balances.get(0).getName();
                                String accountNo = balances.get(0).getAccountNo();
                                String amount =  String.valueOf(balances.get(0).getAmmount());

                                editTextName.setText(balanceName);
                                editTextAccountNo.setText(accountNo);
                                editTexAvailableBalance.setText(amount);

                            }else{

                                Toast.makeText(Deposite.this,"Your User Name Is Not Currect ",Toast.LENGTH_SHORT).show();

                            }

                        }
                        @Override
                        public void onFailure(Call<List<Balance>> call, Throwable t) {

                        }
                    });
                    //calculate tatol amount
                        buttonTotal.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                               String  amount=editTexDepositAmount.getText().toString();

                                if( ! amount.isEmpty()){
                                try {
                                    availableBalance  =Integer.parseInt(editTexAvailableBalance.getText().toString()) ;
                                    depoAmmount  =Integer.parseInt(editTexDepositAmount.getText().toString()) ;
                                }catch (Exception ex) {

                                    return;
                                }
                                    if(depoAmmount>=50){
                                        int totalAmount =availableBalance+depoAmmount;
                                        editTextTotalAmount.setText(String.valueOf(totalAmount));

                                    }else{

                                        Toast.makeText(Deposite.this,"Deposit Amount Minimum 50 Tk.",Toast.LENGTH_SHORT).show();

                                    }

                                }else{

                                    Toast.makeText(Deposite.this,"Deposit Amount Is Empty",Toast.LENGTH_SHORT).show();

                                }

                            }
                        });

                }else{

                    Toast.makeText(Deposite.this,"User Name Is Empty",Toast.LENGTH_SHORT).show();
                    clearfild();
                }

            }
        });

        if(true){
            //deposit balance
            buttonDeposit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        String result= editTextTotalAmount.getText().toString();
                        int depoBalance=Integer.parseInt(result);
                        service2 = AppConnection.getRetrofitInstance().create(GetDataService.class);
                        call2 =service2.updateDepositAmount(depoBalance,id);
                        Toast.makeText(Deposite.this,"Deposit SuccessFull",Toast.LENGTH_SHORT).show();

                        call2.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {

                            }
                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });

                        clearfild();

                    }catch (Exception ex){

                        Toast.makeText(Deposite.this,"Total Amount Is Empty",Toast.LENGTH_SHORT).show();

                    }

                }
            });

        }


    }
}
