package com.example.mywallpaperchangerapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Button wpchange_btn;
    Drawable drawable;
    WallpaperManager wpm;
    Timer timer;
    int prev = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wpchange_btn = (Button) findViewById(R.id.btn_wpchange);
        timer = new Timer();
        wpm = WallpaperManager.getInstance(this);

}

    public void changeWallpaper(View view)
    {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (prev == 1) {


                    drawable = getDrawable(R.drawable.one);
                    prev = 2;
                } else if (prev == 2) {
                    drawable = getDrawable(R.drawable.two);
                    prev = 3;
                } else if (prev == 3) {
                    drawable = getDrawable(R.drawable.three);
                    prev = 4;
                } else if (prev == 4) {
                    drawable = getDrawable(R.drawable.four);
                    prev = 5;
                } else if (prev == 5) {
                    drawable = getDrawable(R.drawable.five);
                    prev = 1;
                }
                Bitmap wallpaper = ((BitmapDrawable)drawable).getBitmap();
                try{
                    wpm.setBitmap(wallpaper);
                }catch(IOException e){
                    Log.e("ERROR",e.getMessage());
                }
            }
        },0,10000);
    }
}


