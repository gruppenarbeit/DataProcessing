package com.example.calibrationapp;

import java.util.Objects;

/**
 * Created by Remo on 11.10.2016.
 */
public class Packet_Exposimeter extends Packet {

    protected byte[] split_packet (int start, int end){

        int length = end - start + 1;
        byte[] splitted = new byte[length];
        for (int i = 0; i < length; i++){
            splitted[i] = packet[i + start];
        }

        return splitted;
    }

    protected byte[] split_packet (int start, int end, byte[] packet){

        int length = end - start + 1;
        byte[] splitted = new byte[length];
        for (int i = 0; i < length; i++){
            splitted[i] = packet[i + start];
        }

        return splitted;
    }

    protected int[] read_rtc_dataInt (byte[] rtc_dataB){

        int[] rtc_data = new int[6];
        for (int i = 0; i < 6; i++){
            rtc_data[i] = (int) rtc_dataB[i];
        }
        return rtc_data;
    }

    protected String read_rtc_dataString(byte[] rtc_dataB){

        int[] rtc_data = read_rtc_dataInt(rtc_dataB);

        String rtc_String = "day: ";
        rtc_String += Integer.toString(rtc_data[0]) + "\n";
        rtc_String += "month:";
        rtc_String += Integer.toString(rtc_data[1]) + "\n";
        rtc_String += "year: ";
        rtc_String += Integer.toString(rtc_data[2]) + "\n";
        rtc_String += "hour: ";
        rtc_String += Integer.toString(rtc_data[3]) + "\n";
        rtc_String += "min: ";
        rtc_String += Integer.toString(rtc_data[4]) + "\n";
        rtc_String += "sec: ";
        rtc_String += Integer.toString(rtc_data[5]) + "\n";

        return rtc_String;

    }

}
