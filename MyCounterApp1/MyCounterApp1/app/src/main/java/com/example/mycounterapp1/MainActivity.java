package com.example.mycounterapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button start_btn,stop_btn,reset_btn;
    TextView counter_txt;
    int count=1;
    boolean isStarted = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        start_btn = (Button)findViewById(R.id.btn_start);
        stop_btn = (Button)findViewById(R.id.btn_stop);
        reset_btn = (Button)findViewById(R.id.btn_reset);
        counter_txt = (TextView)findViewById(R.id.txt_counter);
        runCounter();


    }
    public void startCounter(View view)
    {
        isStarted = true;
    }
    public void stopCounter(View view)
    {
        isStarted = false;
    }
    public void resetCounter(View view)
    {
        isStarted = false;
        count = 0;
        counter_txt.setText(" " + count);
    }
    private void runCounter()
    {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(isStarted)
                {
                    counter_txt.setText(" " + count);
                    count++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }
}