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

        // Get a reference to the same shared preferences as MainActivity
        myPreferenceRef = getPreferences(MODE_PRIVATE);

        // Display preferences
        TextView prefTextRef = findViewById(R.id.prefText);
        String preferenceValue = myPreferenceRef.getString("MyAppPreferenceString", "No preference found.");
        prefTextRef.setText(preferenceValue);

        // Log retrieved preference value
        Log.d("==>", "Retrieved preference value: " + preferenceValue);
    }
}
