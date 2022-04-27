package com.example.covidarchives;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeScreen extends AppCompatActivity {

    EditText countryName,startDate,endDate;
    Button retrieve;
    private DatePickerDialog date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        retrieve = findViewById(R.id.retrieveDateBtn);
        countryName = findViewById(R.id.countryName);
        startDate = findViewById(R.id.startDate);
        endDate = findViewById(R.id.endDate);

        initDatePicker();
    }


    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

            }
        };
    }


    public void getData(){

        String url = "https://api.covid19api.com/country/CANADA/status/confirmed/live?from="; /*+ startdate + "T00:00:00Z&to=" + enddate + "T00:00:00:";*/
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (response.isSuccessful()) {
                    String res = response.body().string();

                    HomeScreen.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //textView.setText(res);
                        }
                    });

                } else {
                    throw new IOException("Unexpected code " + response);
                }
            }
        });
    }
}