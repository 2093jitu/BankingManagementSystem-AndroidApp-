package com.example.bankingmanagementapp;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.bankingmanagementapp.connection.AppConnection;
import com.example.bankingmanagementapp.model.Statement;
import com.example.bankingmanagementapp.service.GetDataService;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatementPage extends AppCompatActivity {

    EditText editText;
    Button button;
    String accountNo;
    ListView listView2;
    TextView textView, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statement_page);

        editText =(EditText) findViewById(R.id.statementSearchAcId);
        button=(Button) findViewById(R.id.statementSearchId);
        listView2=(ListView) findViewById(R.id.statementListViewId);

         textView = (TextView) findViewById(R.id.statementNameId);
         textView2 = (TextView) findViewById(R.id.statementAcNoId);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                accountNo=editText.getText().toString();
                if(!accountNo.isEmpty()){

                    Toast.makeText(StatementPage.this,accountNo,Toast.LENGTH_SHORT).show();
                    GetDataService service = AppConnection.getRetrofitInstance().create(GetDataService.class);
                    Call<List<Statement>> call= service.getSelectedAcStatement(accountNo);

                    call.enqueue(new Callback<List<Statement>>() {
                        @Override
                        public void onResponse(Call<List<Statement>> call, Response<List<Statement>> response) {
                            Log.d("Statement Account = " ,response.body().toString());
                            if(response.body().size()>0){

                                List<Statement> statements =response.body();
                                Log.d("Statement Account = " ,statements.toString());
                                textView.setText("Name : "+statements.get(0).getName().toString());
                                textView2.setText("Account No : "+statements.get(0).getAccountNo().toString());
                                CustomAdapterForStatement adapter = new CustomAdapterForStatement(statements,getApplicationContext());
                                listView2.setAdapter(adapter);



                            }else{

                                Toast.makeText(StatementPage.this,"Yor Account No Do'nt Exgist",Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<List<Statement>> call, Throwable t) {

                        }
                    });

                }else{

                    Toast.makeText(StatementPage.this,"Account No Is Empty",Toast.LENGTH_SHORT).show();

                }


            }
        });


    }
}
