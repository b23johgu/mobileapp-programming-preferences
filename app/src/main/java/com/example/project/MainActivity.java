package com.example.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences myPreferenceRef;
    private SharedPreferences.Editor myPreferenceEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button b = findViewById(R.id.prefButton);

        myPreferenceRef = getPreferences(MODE_PRIVATE);
        myPreferenceEditor = myPreferenceRef.edit();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Update preferences
                myPreferenceEditor.putString("MyAppPreferenceString", "New preference value");
                myPreferenceEditor.apply();

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (myPreferenceRef != null) {
            TextView prefTextRef = findViewById(R.id.prefText);
            prefTextRef.setText(myPreferenceRef.getString("MyAppPreferenceString", "No preference found."));
        }
    }
}
