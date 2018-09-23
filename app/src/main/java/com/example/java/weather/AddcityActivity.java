package com.example.java.weather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;


public class AddcityActivity extends AppCompatActivity {
    EditText addET;

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcity);
        addET = (EditText) findViewById(R.id.addCityET);

    }

    public void goBack(View view) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("city", addET.getText().toString());
        editor.apply();
        editor.commit();
        Intent intent = new Intent(AddcityActivity.this, MainActivity.class);
        startActivity(intent);
    }
}


