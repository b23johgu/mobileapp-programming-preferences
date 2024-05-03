
# Rapport

Added a textview, editview and button to activity_main.xml
```
    <Button
        android:id="@+id/prefButton"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@+id/settingseditview"
        android:layout_marginBottom="340dp"
        android:text="@string/save"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.498"
        app:layout_constraintStart_toStartOf="parent" />

    <EditText
        android:id="@+id/settingseditview"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="132dp"
        android:hint="Write something.."
        android:minHeight="48dp"
        app:layout_constraintBottom_toTopOf="@+id/prefButton"
        app:layout_constraintTop_toBottomOf="@+id/toolbar"
        app:layout_constraintVertical_bias="0.0"
        tools:layout_editor_absoluteX="-16dp" />

    <TextView
        android:id="@+id/prefText"
        android:layout_width="300dp"
        android:layout_height="wrap_content"
        android:gravity="center_horizontal"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.153" />
```

In onCreate in MainActivity the button and edittext was connected to variables. 
SharedPreferences and its editor was initialized.
After that it loads and displays the preference value.
```
        Button b = findViewById(R.id.prefButton);
        prefTextRef = findViewById(R.id.prefText);

        myPreferenceRef = getSharedPreferences("myPreferences", MODE_PRIVATE);
        myPreferenceEditor = myPreferenceRef.edit();

        String preferenceValue = myPreferenceRef.getString("preferenceValue", "No preference found.");
        prefTextRef.setText(preferenceValue);
```
A second activity was created.
[MainActivity2.java](MainActivity2.java)
[activity_main2.xml](activity_main2.xml)

A onClick was connected to the button. It gets the text from edittext, then it stores the new preference value.
After that it updates the textview with the new preference value. At last it clears the edittext.
It will also trigger an intent that will open MainActivity2.
```
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
```
[Screenshot of MainActivity](Screenshots/screenshotmainactivity.png)

MainActivity2´s onCreate is almost identical to MainActivity. It reads the data that was sent from MainActivity and displays it in a textview that was implemented in activity_main2.xml with the same id as in activity_main.xml.
```
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        myPreferenceRef = getSharedPreferences("myPreferences", MODE_PRIVATE);

        String preferenceValue = myPreferenceRef.getString("preferenceValue", "No preference found.");

        TextView prefTextRef = findViewById(R.id.prefText);
        prefTextRef.setText(preferenceValue);
    }
```
[Screenshot of MainActivity2](Screenshots/screenshotmainactivity2.png)

When closing SecondActivity the data written should be visible in MainActivity. 
To do this an onResume was implemented in MainActivity that loads and displays the data that was sent when the activity is resumed.


```
    @Override
    protected void onResume() {
        super.onResume();
        
        String preferenceValue = myPreferenceRef.getString("preferenceValue", "No preference found.");
        prefTextRef.setText(preferenceValue);
    }
```