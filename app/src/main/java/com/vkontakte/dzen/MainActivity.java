package com.vkontakte.dzen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.vkontakte.dzen.di.ActivityInjection;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityInjection.getInstance().add(getClass(), this);

        startActivity(new Intent(this, SelectionActivity.class));
    }
}