package com.vkontakte.dzen.di;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class ActivityInjection {
    private static ActivityInjection instance;

    private final Map<String, AppCompatActivity> instances = new HashMap<>();

    public static ActivityInjection getInstance(){
        if(instance == null) {
            instance = new ActivityInjection();
        }
        return instance;
    }

    private ActivityInjection() {}

    public void add(Class<? extends AppCompatActivity> clazz, AppCompatActivity activity) {
        instances.put(clazz.getName(), activity);
    }

    public AppCompatActivity get(Class<? extends AppCompatActivity> clazz) {
        return instances.getOrDefault(clazz.getName(), null);
    }
}
