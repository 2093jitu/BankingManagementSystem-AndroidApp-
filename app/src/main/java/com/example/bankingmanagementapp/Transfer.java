package com.example.bankingmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bankingmanagementapp.connection.AppConnection;
import com.example.bankingmanagementapp.model.Account;
import com.example.bankingmanagementapp.model.Balance;
import com.example.bankingmanagementapp.model.PostTransfer;
import com.example.bankingmanagementapp.service.GetDataService;
import com.example.bankingmanagementapp.service.SpinnerCustomAdepter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Transfer extends AppCompatActivity {


    Spinner spinner;

    EditText editTextName,editTextAccountNo,
            editTextAvailableBalance,editTextTransferAmount,editTextDebitAccCurrentBalance,
            editTextCreditAccNo,editTextCreditAccCurrentBalance,editTextcreditAccTotalBalance;
    Button  buttonSearch,buttonTotal,buttonShow,buttonTransfer;
    AutoCompleteTextView editTextUsername;

    int id;
    GetDataService service,service2,autuComplit;
    Call<List<Balance>> call;
    List <Balance> allBalance;
    Call<List<String>> allUserName;
    List<String> name;
    String userName;
    boolean ifFastSelection=true;
    String selecedAccountNo;
    public  void clearFild(){

        editTextUsername.setText("");
        editTextName.setText("");
        editTextAccountNo.setText("");
        editTextAvailableBalance.setText("");
        editTextTransferAmount.setText("");
        editTextDebitAccCurrentBalance.setText("");
        editTextCreditAccCurrentBalance.setText("");
        editTextcreditAccTotalBalance.setText("");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        editTextUsername = (AutoCompleteTextView) findViewById(R.id.transfeSearchUsernameId);
        buttonSearch = (Button) findViewById(R.id.transferSearchId);

        editTextName = (EditText) findViewById(R.id.transferNameId);
        editTextAccountNo = (EditText) findViewById(R.id.transferAccountNoId);
        editTextAvailableBalance = (EditText) findViewById(R.id.transferAvailableBalanceId);
        editTextTransferAmount = (EditText) findViewById(R.id.transferAmountId);
        buttonTotal = (Button) findViewById(R.id.transferTotalAmountButtonId);

        editTextDebitAccCurrentBalance = (EditText) findViewById(R.id.transferDebitAccCurrentAmountId);
        editTextCreditAccCurrentBalance = (EditText) findViewById(R.id.creditAccCurrentBalanceId);
        editTextcreditAccTotalBalance = (EditText) findViewById(R.id.creditAccTotalBalanceId);

        buttonShow = (Button) findViewById(R.id.transferShowButtonId);
        buttonTransfer = (Button) findViewById(R.id.transferButtonId);
        spinner=(Spinner) findViewById(R.id.spinnerId);


        autuComplit = AppConnection.getRetrofitInstance().create(GetDataService.class);
        allUserName = autuComplit.getAllUserName();

        allUserName.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {

                name=response.body();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.custom_list_item_for_withdrow,R.id.text_view_list_item_withdrow,name);
                editTextUsername.setAdapter(adapter);
                Log.d("Auto Complit Trnasfer" ,name.toString());
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userName = editTextUsername.getText().toString();
                //copy
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

                                //end
                                buttonTotal.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        try {

                                            int availableBalance  = Integer.parseInt(editTextAvailableBalance.getText().toString());
                                            String amount =editTextTransferAmount.getText().toString();

                                            if( ! amount.isEmpty()){

                                                int tAmmount = Integer.parseInt(amount);

                                                if(availableBalance>tAmmount && tAmmount>0){

                                                    int dtAccountCurrentbalance = availableBalance-tAmmount;
                                                    editTextDebitAccCurrentBalance.setText(String.valueOf(dtAccountCurrentbalance));

                                                }else{

                                                    Toast.makeText(Transfer.this,"You Have Not Avable Balance",Toast.LENGTH_SHORT).show();

                                                }

                                            }else{

                                                Toast.makeText(Transfer.this,"Tranasfer Amount Is Empty",Toast.LENGTH_SHORT).show();
                                                clearFild();

                                            }

                                        }catch (Exception ex){

                                            return;

                                        }
                                        //start
                                        if(!editTextDebitAccCurrentBalance.getText().toString().isEmpty()){

                                            GetDataService service = AppConnection.getRetrofitInstance().create(GetDataService.class);
                                            Call<List<Balance>> callallBalance = service.getAllBalance();

                                            callallBalance.enqueue(new Callback<List<Balance>>() {
                                                @Override
                                                public void onResponse(Call<List<Balance>> call, Response<List<Balance>> response) {


                                                    allBalance=response.body();
                                                    Log.d("All Balance Transfer" ,allBalance.toString());

                                                    SpinnerCustomAdepter adepter = null;

                                                    try {

                                                        adepter = new SpinnerCustomAdepter(allBalance,getApplicationContext());

                                                    }catch(Exception ex){

                                                    }

                                                    spinner.setAdapter(adepter);

                                                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                        @Override
                                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                                                            if(ifFastSelection ==true){

                                                                ifFastSelection=false;
                                                            }
                                                            else{
                                                                selecedAccountNo=allBalance.get(i).getAccountNo();
                                                                GetDataService    service3 = AppConnection.getRetrofitInstance().create(GetDataService.class);
                                                                Call<List<Account>> call3= service3.getAccountByAccountNo(selecedAccountNo);

                                                                call3.enqueue(new Callback<List<Account>>() {
                                                                    @Override
                                                                    public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {

                                                                        List<Account> result =response.body();
                                                                        String resultfainal=String.valueOf(result.get(0).getBalance().getAmmount());
                                                                        editTextCreditAccCurrentBalance.setText(resultfainal);

                                                                    }
                                                                    @Override
                                                                    public void onFailure(Call<List<Account>> call, Throwable t) {

                                                                    }
                                                                });
                                                            }
                                                        }
                                                        @Override
                                                        public void onNothingSelected(AdapterView<?> adapterView) {

                                                        }
                                                    });
                                                }
                                                @Override
                                                public void onFailure(Call<List<Balance>> call, Throwable t) {

                                                }
                                            });

                                            buttonShow.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {

                                                    String trnasgerAmount=   editTextTransferAmount.getText().toString();
                                                    String CreditAccCurrentBalance=editTextCreditAccCurrentBalance.getText().toString();

                                                    if(!trnasgerAmount.isEmpty()){

                                                        int result =Integer.parseInt(trnasgerAmount)+Integer.parseInt(CreditAccCurrentBalance);
                                                        String result2=String.valueOf(result);
                                                        editTextcreditAccTotalBalance.setText(result2);

                                                    }

                                                }
                                            });

                                        }


                                    }
                                });

                            }else {

                                Toast.makeText(Transfer.this,"Username not found",Toast.LENGTH_SHORT).show();
                                clearFild();
                            }

                        }

                        @Override
                        public void onFailure(Call<List<Balance>> call, Throwable t) {


                        }
                    });
                }else{

                    Toast.makeText(Transfer.this,"User Name Is Empty",Toast.LENGTH_SHORT).show();
                    clearFild();

                }







            }
        });

        //transfer button
        buttonTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    String transferAmountString=editTextTransferAmount.getText().toString();
                    int transferAmount =Integer.parseInt(transferAmountString);

                    String dRtotalAmountStrinh =editTextDebitAccCurrentBalance.getText().toString();
                    int dRtotalAmount =Integer.parseInt(dRtotalAmountStrinh);

                    String cRcurrentAcBalanceString=editTextCreditAccCurrentBalance.getText().toString();
                    int cRcurrentAcBalance=Integer.parseInt(cRcurrentAcBalanceString) ;

                    String selecterAccountNo=selecedAccountNo;

                    String cRAcTotalBalanceString =editTextcreditAccTotalBalance.getText().toString();
                    int cRAcTotalBalance =Integer.parseInt(cRAcTotalBalanceString);

                    String drAccountNo =editTextAccountNo.getText().toString();

                    PostTransfer postTransfer = new PostTransfer(id,transferAmount,dRtotalAmount,selecterAccountNo,cRcurrentAcBalance,cRAcTotalBalance,drAccountNo);
                    Log.d("postTransfer ==" ,postTransfer.toString());
                    GetDataService    service4 = AppConnection.getRetrofitInstance().create(GetDataService.class);
                    Call<PostTransfer> call4= service4.postDtaForTransfer(postTransfer);

                    call4.enqueue(new Callback<PostTransfer>() {
                        @Override
                        public void onResponse(Call<PostTransfer> call, Response<PostTransfer> response) {

                            //response.body();
                            if(! response.body().getDrAccountNo().toString().isEmpty()){

                                Toast.makeText(Transfer.this,"Balance Tranasfer SuccessFull",Toast.LENGTH_SHORT).show();
                                clearFild();

                            }


                        }

                        @Override
                        public void onFailure(Call<PostTransfer> call, Throwable t) {

                        }
                    });


                }catch (Exception ex){

                    Toast.makeText(Transfer.this,"Empty Is Not Alow",Toast.LENGTH_SHORT).show();

                }



            }
        });


    }
}
