package com.example.calibrationapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_results);

        Intent intent = getIntent();
        String extra_message1 = intent.getStringExtra(MainActivity.EXTRA_MESSAGE1);
        String extra_message2 = intent.getStringExtra(MainActivity.EXTRA_MESSAGE2);
        String extra_message4 = intent.getStringExtra(MainActivity.EXTRA_MESSAGE3);
        String extra_message5 = intent.getStringExtra(MainActivity.EXTRA_MESSAGE4);
        String message1 = "freq = " + extra_message1;
        String message2 = "magn = " + extra_message2;
        String message3 = "field_strength = ";
        String message4 = "dev.id = " + extra_message4;
        String message5 = "att. = " + extra_message5;
        String message6 = "packet = ";

        //Calibration
        Calibration calibration = new Calibration();
        double field_strength = calibration.get_field_strength(Double.parseDouble(extra_message1), Double.parseDouble(extra_message2));

        if (field_strength == -1){
            message3 = message3 + "freq. out of bound";
        }
        else if (field_strength == -2){
            message3 = message3 + "magn. out of bound";
        }
        else{
            message3 = message3 + Double.toString(field_strength);
        }


        //generating Packet
        Build_Packet_Test test = new Build_Packet_Test(53423);
        Cal_Packet_Exposi test1 = new Cal_Packet_Exposi(test.test_packet);
        Cal_Packet_Trigger Packet = new Cal_Packet_Trigger(Integer.parseInt(extra_message4), Integer.parseInt(extra_message5));

        TextView input_freq = (TextView) findViewById(R.id.input_freq);
        TextView input_magn = (TextView) findViewById(R.id.input_magn);
        TextView output = (TextView) findViewById(R.id.output);
        TextView attenuator = (TextView) findViewById(R.id.attenuator);
        TextView device_id = (TextView) findViewById(R.id.device_id);
        TextView output2 = (TextView) findViewById(R.id.output2);

        input_freq.setText(test1.get_device_name());
        input_magn.setText(String.valueOf(test1.get_rtc_dataInt()));
        output.setText(test1.get_rtc_dataString());

        attenuator.setText(String.valueOf(test1.get_attenuator()));
        device_id.setText(String.valueOf(test1.get_table_number()));
       //output2.setText(String.valueOf(test1.get_caliTable(2)));
    }


}