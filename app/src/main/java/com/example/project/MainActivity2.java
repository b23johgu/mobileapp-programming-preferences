package com.example.project;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private SharedPreferences myPreferenceRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        myPreferenceRef = getSharedPreferences("myPreferences", MODE_PRIVATE);

        String preferenceValue = myPreferenceRef.getString("preferenceValue", "No preference found.");

        TextView prefTextRef = findViewById(R.id.prefText);
        prefTextRef.setText(preferenceValue);
    }
}
