package com.example.mytexttospeechconverter2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText text_edtxt;



  TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_edtxt = (EditText)findViewById(R.id.edtxt_text);
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener(){
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                  textToSpeech.setLanguage(Locale.UK);
                }
            }
        });
    }
    public void text2speech(View view) {
        String text = text_edtxt.getText().toString();
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, "SPEAK");
        Toast.makeText(this, "Running is Succesful", Toast.LENGTH_SHORT).show();

    }
    protected void onDestroy() {
        super.onDestroy();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}

