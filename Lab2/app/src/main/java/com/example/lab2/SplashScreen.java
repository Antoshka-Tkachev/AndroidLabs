package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        int orientation = this.getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            new DownloadImage((ImageView) findViewById(R.id.imageView)).execute("https://images.wallpaperscraft.ru/image/kosmos_kosmicheskoe_prostranstvo_svet_blesk_94206_1080x1920.jpg");
        } else {
            new DownloadImage((ImageView) findViewById(R.id.imageView)).execute("https://cdn.wallpaperhi.com/2560x1440/20120607/outer%20space%20gods%202560x1440%20wallpaper_www.wallpaperhi.com_7.jpg");
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!flag) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(8000);
                    } catch (Exception e) {
                        Log.d("myLog", "exception");
                    }
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }).start();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("flag", true);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        flag = savedInstanceState.getBoolean("flag");
    }
}
