package com.example.calibrationapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE1 = "com.example.myfirstapp.MESSAGE1";
    public final static String EXTRA_MESSAGE2 = "com.example.myfirstapp.MESSAGE2";
    public final static String EXTRA_MESSAGE3 = "com.example.myfirstapp.MESSAGE3";
    public final static String EXTRA_MESSAGE4 = "com.example.myfirstapp.MESSAGE4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Called when the user clicks the Send button
     */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayResults.class);

        EditText editText1 = (EditText) findViewById(R.id.input_freq);
        EditText editText2 = (EditText) findViewById(R.id.input_magn);
        EditText editText3 = (EditText) findViewById(R.id.device_id);
        EditText editText4 = (EditText) findViewById(R.id.attenuator);

        String input_freq = editText1.getText().toString();
        String input_magn = editText2.getText().toString();
        String device_id = editText3.getText().toString();
        String attenuator = editText4.getText().toString();

        intent.putExtra(EXTRA_MESSAGE1, input_freq);
        intent.putExtra(EXTRA_MESSAGE2, input_magn);
        intent.putExtra(EXTRA_MESSAGE3, device_id);
        intent.putExtra(EXTRA_MESSAGE4, attenuator);

        startActivity(intent);
    }

}
