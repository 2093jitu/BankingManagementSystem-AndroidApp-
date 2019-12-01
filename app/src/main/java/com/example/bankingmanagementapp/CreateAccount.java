package com.example.bankingmanagementapp;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bankingmanagementapp.connection.AppConnection;
import com.example.bankingmanagementapp.model.CreateAccountForApp;
import com.example.bankingmanagementapp.model.PostTransfer;
import com.example.bankingmanagementapp.service.GetDataService;

public class CreateAccount extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton genderButton;
    EditText editTextAcNo,editTextMircNo,editTextPinNo,editTextName,editTextAmount,editTextPhonNo;
    Button buttonCreate;
    String gender,accountNo,mircNo,pinNo,amount,name,phonNo,accountType;
    String [] adapterAccountType ={"Running","Current"};
    Spinner spinner;
    int amountI;

    public void clear(){

        editTextAcNo.setText("");
        editTextMircNo.setText("");
        editTextPinNo.setText("");
        editTextAmount.setText("");
        editTextName.setText("");
        editTextPhonNo.setText("");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);


        radioGroup=(RadioGroup) findViewById(R.id.radioGroupId);
        buttonCreate=findViewById(R.id.createAcCrButtonId);
        editTextAcNo=(EditText) findViewById(R.id.createAcAcNoId);
        editTextMircNo=(EditText) findViewById(R.id.createAcMircNoId);
        editTextPinNo=(EditText) findViewById(R.id.createAcPinNoId);
        editTextAmount=(EditText) findViewById(R.id.createAcAmountId);
        editTextName=(EditText) findViewById(R.id.createAcNameId);
        editTextPhonNo=(EditText) findViewById(R.id.createAcPhonNoId);
        spinner=(Spinner) findViewById(R.id.createAcSpinnerId);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,adapterAccountType);
        spinner.setAdapter(aa);

        final Intent intent =getIntent();
        editTextAcNo.setText(intent.getStringExtra("accountNo"));
        editTextMircNo.setText(intent.getStringExtra("mircNo"));
        editTextPinNo.setText(intent.getStringExtra("pinNo"));

         buttonCreate.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               try {

                   int selected=  radioGroup.getCheckedRadioButtonId();
                   genderButton=(RadioButton) findViewById(selected);
                   gender= genderButton.getText().toString();
                   accountNo= editTextAcNo.getText().toString();
                   mircNo= editTextMircNo.getText().toString();
                   int mircNoI=Integer.parseInt(mircNo);
                   pinNo= editTextPinNo.getText().toString();
                   int pinNoI=Integer.parseInt(pinNo);
                   try {
                       amount=  editTextAmount.getText().toString();
                       amountI=Integer.parseInt(amount);

                   }catch (Exception ex){

                       Toast.makeText(CreateAccount.this,"Amount Must Be A Number",Toast.LENGTH_SHORT).show();
                       amount=null;
                       return;
                   }

                   name= editTextName.getText().toString();
                   phonNo=editTextPhonNo.getText().toString();
                   accountType=spinner.getSelectedItem().toString();

                   if(!phonNo.isEmpty()&&!gender.isEmpty() && !accountNo.isEmpty() && !mircNo.isEmpty()&& !pinNo.isEmpty() && !amount.isEmpty() && !name.isEmpty()){

                       CreateAccountForApp account = new CreateAccountForApp( gender, accountNo, mircNoI, pinNoI, amountI,  name, phonNo, accountType);
                       Log.d("okkkkkkkkkkkkkk Depo" ,account.toString());
                       GetDataService service = AppConnection.getRetrofitInstance().create(GetDataService.class);
                       Call<CreateAccountForApp> call= service.createAccountForApp(account);

                       call.enqueue(new Callback<CreateAccountForApp>() {
                           @Override
                           public void onResponse(Call<CreateAccountForApp> call, Response<CreateAccountForApp> response) {

                               Toast.makeText(CreateAccount.this,"Account Created",Toast.LENGTH_SHORT).show();

                           }

                           @Override
                           public void onFailure(Call<CreateAccountForApp> call, Throwable t) {

                           }
                       });
                       clear();
                   }
                   else{

                       Toast.makeText(CreateAccount.this,"Empty Is Not Alow",Toast.LENGTH_SHORT).show();
                   }

               }catch (Exception ex){

                   Toast.makeText(CreateAccount.this,"Empty Is Not Alow",Toast.LENGTH_SHORT).show();

               }
           }
       });


    }
}
