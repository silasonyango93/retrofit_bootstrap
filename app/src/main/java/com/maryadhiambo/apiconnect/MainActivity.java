package com.maryadhiambo.apiconnect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText etFirstName = findViewById(R.id.et_first_name);
        EditText etMiddleName = findViewById(R.id.et_middle_name);
        EditText etSurname = findViewById(R.id.et_surname);
        EditText etEmail = findViewById(R.id.et_email);
        Button btnSubmit = findViewById(R.id.submit_button);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitData(etFirstName.getText().toString(),
                        etMiddleName.getText().toString(),
                        etSurname.getText().toString(),
                        etEmail.getText().toString()
                );
            }
        });
    }

    public void submitData(String firstName, String middleName, String surname, String email) {
        Repository repository = RetrofitClientInstance.getRetrofitInstance(EndPoints.BASE_URL).create(Repository.class);
        Call<SuccessfulInsertionResponse> call = repository.insertUsers(firstName,middleName,surname,email);
        call.enqueue(new Callback<SuccessfulInsertionResponse>() {
            @Override
            public void onResponse(Call<SuccessfulInsertionResponse> call, retrofit2.Response<SuccessfulInsertionResponse> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    Toast.makeText(getBaseContext(), "Succesfully inserted user", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<SuccessfulInsertionResponse> call, Throwable t) {
                System.out.println();
            }
        });
    }
}