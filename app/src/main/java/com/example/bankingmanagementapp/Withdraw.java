package com.example.bankingmanagementapp;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bankingmanagementapp.connection.AppConnection;
import com.example.bankingmanagementapp.model.Balance;
import com.example.bankingmanagementapp.service.GetDataService;

import java.util.List;

public class Withdraw extends AppCompatActivity {

    EditText editTextName,editTextAccountNo,editTextAvailableBalance,editTextWithdrowAmount,editTextTotalAmount;
    AutoCompleteTextView editText;
    Button buttonSearch,buttonTotal,buttonWithdraw;
    int id;
    GetDataService service,service2,autuComplit;
    Call<List<Balance>> call;
    Call <String> call2;
    int availableBalance;
    int WithdrowAmount;
    int totalAmmount;
    Call<List<String>> allUserName;
    List<String> name;
    String userName;

    public  void clearFild(){

        editText.setText("");
        editTextName.setText("");
        editTextAccountNo.setText("");
        editTextAvailableBalance.setText("");
        editTextWithdrowAmount.setText("");
        editTextTotalAmount.setText("");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);

        editText = (AutoCompleteTextView) findViewById(R.id.withdrawSearchUsernameId);
        buttonSearch = (Button) findViewById(R.id.withdrawSearchId);
        editTextName = (EditText) findViewById(R.id.withdrawNameId);
        editTextAccountNo = (EditText) findViewById(R.id.withdrawAccountNoId);
        editTextAvailableBalance = (EditText) findViewById(R.id.withdrawAvailableBalanceId);
        editTextWithdrowAmount=(EditText) findViewById(R.id.withdrawAmountId);
        editTextTotalAmount=(EditText) findViewById(R.id.withdrawTotalAmountId);
        buttonTotal=(Button) findViewById(R.id.withdrawTotalAmountButtonId);
        buttonWithdraw=(Button) findViewById(R.id.withdrawButtonId);


        autuComplit = AppConnection.getRetrofitInstance().create(GetDataService.class);
        allUserName = autuComplit.getAllUserName();

        allUserName.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {

                name=response.body();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.custom_list_item_for_withdrow,R.id.text_view_list_item_withdrow,name);
                editText.setAdapter(adapter);
                Log.d("Auto Complit Withdrow" ,name.toString());
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });

        buttonSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                userName = editText.getText().toString();

                if( !userName .isEmpty()){

                    service = AppConnection.getRetrofitInstance().create(GetDataService.class);
                    call = service.getBalance(userName);

                    call.enqueue(new Callback<List<Balance>>() {

                        @Override
                        public void onResponse(Call<List<Balance>> call, Response<List<Balance>> response) {

                            if(response.body().size()>0){
                                List<Balance> balances = response.body();
                                id=balances.get(0).getId();
                                String name = balances.get(0).getName();
                                String accountNo = balances.get(0).getAccountNo();
                                String availableBalance = String.valueOf(balances.get(0).getAmmount());

                                editTextName.setText(name);
                                editTextAccountNo.setText(accountNo);
                                editTextAvailableBalance.setText(availableBalance);
                                Log.d("Okayyyyyyyy Withdraw" ,balances.toString());

                            }else {

                                Toast.makeText(Withdraw.this,"Username not found",Toast.LENGTH_SHORT).show();
                                clearFild();
                            }

                        }

                        @Override
                        public void onFailure(Call<List<Balance>> call, Throwable t) {


                        }
                    });
                }else{

                    Toast.makeText(Withdraw.this,"User Name Is Empty",Toast.LENGTH_SHORT).show();
                    clearFild();

                }

            }
        });


        //Calculate Total Amount
        buttonTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


             String amount =editTextWithdrowAmount.getText().toString();

             if( ! amount.isEmpty() ){
                 try {
                     availableBalance  = Integer.parseInt(editTextAvailableBalance.getText().toString());
                     WithdrowAmount =Integer.parseInt(editTextWithdrowAmount.getText().toString());

                 }catch (Exception ex){

                    return;
                 }

                 if(availableBalance>WithdrowAmount && WithdrowAmount>0){
                      totalAmmount =availableBalance-WithdrowAmount;
                      editTextTotalAmount.setText(String.valueOf(totalAmmount));

                 }else if(WithdrowAmount<=0){

                     Toast.makeText(Withdraw.this,"Withdrow Amount Mustbe More Than 0 ",Toast.LENGTH_SHORT).show();

                 }
                 else{

                     Toast.makeText(Withdraw.this,"You Have Not AvailableBalance For Withdrow",Toast.LENGTH_SHORT).show();

                 }

             }else{

                 Toast.makeText(Withdraw.this,"Withdrow Amount Is Empty",Toast.LENGTH_SHORT).show();
                 //clearFild();
             }

            }
        });

        //balance Withdraw
        buttonWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    String result = editTextTotalAmount.getText().toString();
                    int depoBalance=Integer.parseInt(result);
                    service2 = AppConnection.getRetrofitInstance().create(GetDataService.class);
                    call2 =service2.updateWithdrawAmount(depoBalance,id);
                    Toast.makeText(Withdraw.this,"Withdrow SuccessFull",Toast.LENGTH_SHORT).show();

                    call2.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {

                        }
                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });

                }catch (Exception ex){

                    Toast.makeText(Withdraw.this,"Withdrow Amount Is Empty",Toast.LENGTH_SHORT).show();
                }

                clearFild();

            }
        });




    }
}
