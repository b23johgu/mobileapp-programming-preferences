package com.example.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences myPreferenceRef;
    private SharedPreferences.Editor myPreferenceEditor;
    private TextView prefTextRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button b = findViewById(R.id.prefButton);
        prefTextRef = findViewById(R.id.prefText);

        myPreferenceRef = getSharedPreferences("myPreferences", MODE_PRIVATE);
        myPreferenceEditor = myPreferenceRef.edit();

        String preferenceValue = myPreferenceRef.getString("preferenceValue", "No preference found.");
        prefTextRef.setText(preferenceValue);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText newPrefText = findViewById(R.id.settingseditview);
                String newText = newPrefText.getText().toString();

                myPreferenceEditor.putString("preferenceValue", newText);
                myPreferenceEditor.apply();

                prefTextRef.setText(newText);

                newPrefText.setText("");

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        String preferenceValue = myPreferenceRef.getString("preferenceValue", "No preference found.");
        prefTextRef.setText(preferenceValue);
    }
}
